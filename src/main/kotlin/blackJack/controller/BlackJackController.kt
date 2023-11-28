package blackJack.controller

import blackJack.domain.card.CardDeck
import blackJack.domain.card.Cards
import blackJack.domain.player.Dealer
import blackJack.domain.player.Participants
import blackJack.domain.player.Player
import blackJack.domain.result.Result
import blackJack.dto.playerDto.DealerDto
import blackJack.dto.playerDto.ParticipantsDto
import blackJack.dto.playerDto.PlayerDto
import blackJack.dto.ResultDto.ResultDto
import blackJack.view.InputView
import blackJack.view.OutputView

fun main() {
    val cardDeck = CardDeck.createShuffledDeck()

    OutputView.printEnterName()
    val inputNames = InputView.inputNames()
    val playerList = Player.splitNames(inputNames)
    OutputView.printPlayer(playerList)

    val participants = Participants.createParticipants(playerList)
    participants.receiveInitialCards { cardDeck.initialCards() }

    val participantsDto = ParticipantsDto(participants)
    OutputView.printPlayerCards(participantsDto)

    playGame(participants, cardDeck)

    val finishGameParticipants = playGame(participants, cardDeck)
    OutputView.printResult(finishGameParticipants)

    val calculateResult = Result.calculateResult(participants)
    val resultDto = ResultDto(calculateResult)
    OutputView.printWinner(resultDto)
}

private fun playGame(participants: Participants, cardDeck: Cards): ParticipantsDto {
    val playGamePlayers = playGamePlayers(participants, cardDeck)
    val playGameDealer = playGameDealer(participants.dealer, cardDeck)
    return ParticipantsDto(playGamePlayers, playGameDealer)
}

private fun playGamePlayers(participants: Participants, cardDeck: Cards): List<PlayerDto> {
    val players = mutableListOf<PlayerDto>()

    participants.players.players.forEach {
        while (it.isContinued()) {
            val isContinue = isContinuePlayer(it)
            it.continueGamePlayer(isContinue, cardDeck)
            OutputView.printPlayerCard(PlayerDto(it))
        }
        players.add(PlayerDto(it, it.getTotalScore()))
    }

    return players
}

private fun isContinuePlayer(player: Player): Boolean {
    val playerDto = PlayerDto(player)
    OutputView.printQuestionYesOrNo(playerDto)
    return InputView.answerYesOrNo() == "y"
}

private fun playGameDealer(dealer: Dealer, cardDeck: Cards): DealerDto {
    if (dealer.isContinued()) {
        dealer.addCard(cardDeck.drawCard())
        OutputView.printPlayerCard(DealerDto(dealer))
    }
    return DealerDto(dealer, dealer.getTotalScore())
}
