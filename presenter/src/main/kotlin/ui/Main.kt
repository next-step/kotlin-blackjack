package ui

import blackjack.deck.Deck
import blackjack.hand.Hand
import blackjack.player.Player
import ui.input.InputView
import ui.result.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val playerNames = inputView.inputPlayerNames()

    val deck = Deck()
    val players = playerNames.map { Player(it, Hand()) }

    playBlackjack(deck, players, inputView, resultView)
}

fun playBlackjack(deck: Deck, players: List<Player>, inputView: InputView, resultView: ResultView) {
    val playingPlayers = resultView.showInitialCards(deck, players).toMutableList()
    println()

    playingPlayers.forEach { player ->
        handlePlayerTurn(deck, player, inputView, resultView)
    }

    resultView.showFinalResults(playingPlayers)
}

fun handlePlayerTurn(deck: Deck, player: Player, inputView: InputView, resultView: ResultView) {
    while (player.canReceiveCard()) {
        if (!inputView.askForAdditionalCard(player.name)) {
            return
        }
        player.drawCard(deck)
        resultView.showHandCards(player)
    }
    println()
}
