package blackJack.controller

import blackJack.domain.card.CardDeck
import blackJack.domain.card.Cards
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player
import blackJack.domain.player.Players
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto
import blackJack.view.InputView
import blackJack.view.OutputView

fun main() {
    val cardDeck = CardDeck.createShuffledDeck()
//    val dealer = Dealer(cardDeck)

    OutputView.printEnterName()
    val inputNames = InputView.inputNames()
    val playerList = Player.splitNames(inputNames)
    OutputView.printPlayer(playerList)

    val players: Players = Players.createPlayers(playerList)
    players.receiveInitialCards { cardDeck.initialCards() }

    val playersDto = PlayersDto(players)
    OutputView.printPlayerCards(playersDto)

    val finishGamePlayers = players.players.map {
        playGame(it, cardDeck)
        PlayerDto(it, it.getTotalScore())
    }
    val playersResult = PlayersDto(finishGamePlayers)
    OutputView.printResult(playersResult)
}

private fun playGame(player: Player, cardDeck: Cards) {
    while (player.isContinued()) {
        val isContinue = isContinuePlayer(player)
        continueGame(isContinue, player, cardDeck)
        OutputView.printPlayerCard(PlayerDto(player))
    }
}

private fun isContinuePlayer(player: Player): Boolean {
    val playerDto = PlayerDto(player)
    OutputView.printQuestionYesOrNo(playerDto)
    return InputView.answerYesOrNo() == "y"
}

private fun continueGame(isContinue: Boolean, player: Player, cardDeck: Cards) {
    if (isContinue) {
        val card = cardDeck.drawCard()
        player.addCard(card)
    } else {
        player.gameStop()
    }
}
