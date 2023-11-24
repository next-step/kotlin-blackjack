package blackJack.controller

import blackJack.domain.card.CardDeck
import blackJack.domain.card.Cards
import blackJack.domain.player.Dealer
import blackJack.domain.player.Participants
import blackJack.domain.player.Player
import blackJack.dto.DealerDto
import blackJack.dto.ParticipantsDto
import blackJack.dto.PlayerDto
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
    val participantsResult = ParticipantsDto(finishGameParticipants)
    OutputView.printResult(participantsResult)
}

private fun playGame(participants: Participants, cardDeck: Cards): Participants {
    playGamePlayers(participants, cardDeck)
    playGameDealer(participants.dealer, cardDeck)
}

private fun playGamePlayers(participants: Participants, cardDeck: Cards) {
    participants.players.players.forEach {
        while (it.isContinued()) {
            val isContinue = isContinuePlayer(it)
            continueGamePlayers(isContinue, it, cardDeck)
            OutputView.printPlayerCard(PlayerDto(it))
        }
        // totalScore 넣기
        PlayerDto(it, it.getTotalScore())
    }
}

private fun isContinuePlayer(player: Player): Boolean {
    val playerDto = PlayerDto(player)
    OutputView.printQuestionYesOrNo(playerDto)
    return InputView.answerYesOrNo() == "y"
}

private fun continueGamePlayers(isContinue: Boolean, player: Player, cardDeck: Cards) {
    if (isContinue) {
        val card = cardDeck.drawCard()
        player.addCard(card)
    } else {
        player.gameStop()
    }
}

private fun playGameDealer(dealer: Dealer, cardDeck: Cards) {
    val dealerTotalScore = dealer.cards.calculateTotalScore()
    if (dealer.isContinued(dealerTotalScore)) {
        continueGameDealer(dealer, cardDeck)
        OutputView.printPlayerCard(DealerDto(dealer))
    }
    DealerDto(dealer, dealerTotalScore)
}

private fun continueGameDealer(dealer: Dealer, cardDeck: Cards) {
    val card = cardDeck.drawCard()
    dealer.addCard(card)
}
