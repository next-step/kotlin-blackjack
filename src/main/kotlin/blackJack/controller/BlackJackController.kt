package blackJack.controller

import blackJack.domain.CardDeck
import blackJack.domain.Dealer
import blackJack.domain.Player
import blackJack.domain.Players
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto
import blackJack.view.InputView
import blackJack.view.OutputView

fun main() {
    val cardDeck = CardDeck.createShuffledDeck()
    val dealer = Dealer(cardDeck)

    OutputView.printEnterName()
    val inputNames = InputView.inputNames()
    val playerList = Player.splitNames(inputNames)
    OutputView.printPlayer(playerList)

    val players: Players = Players.createPlayers(playerList)
    players.receiveInitialCards { dealer.initialCards() }

    val playersDto = PlayersDto(players)
    OutputView.printPlayerCards(playersDto)

    val finishGamePlayers = players.players.map {
        playGame(it, dealer)
        PlayerDto(it, it.getTotalScore())
    }
    val playersResult = PlayersDto(finishGamePlayers)
    OutputView.printResult(playersResult)
}

private fun playGame(player: Player, dealer: Dealer) {
    while (player.isContinued()) {
        val isContinue = isReadyPlayer(player)
        continueGame(isContinue, player, dealer)
        OutputView.printPlayerCard(PlayerDto(player))
    }
}

private fun isReadyPlayer(player: Player): Boolean {
    val playerDto = PlayerDto(player)
    OutputView.printQuestionYesOrNo(playerDto)
    return InputView.answerYesOrNo() == "y"
}

private fun continueGame(isContinue: Boolean, player: Player, dealer: Dealer) {
    if (isContinue) {
        val card = dealer.drawCard()
        player.addCard(card)
    } else {
        player.gameStop()
    }
}
