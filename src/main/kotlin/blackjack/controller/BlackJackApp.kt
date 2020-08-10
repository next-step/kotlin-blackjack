package blackjack.controller

import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = registerPlayers()

    drawDeck(players)
    showResult(players)
}

private fun showResult(players: List<Player>) {
    players.map { ResultView.printResult(it, it.calculateRank()) }
}

private fun drawDeck(players: List<Player>) {
    players.map {
        while (isContinueDraw(it)) {
            it.requestDeck(Deck.pop())
            ResultView.printPlayerHaveDeck(it)
        }
    }
}

private fun isContinueDraw(player: Player) =
    player.calculateRank() < Player.MAX_RANK && InputView.requestOneOfDeck(player) == "y"

private fun registerPlayers(): List<Player> {
    val playerName = InputView.requestPlayerNames()
    val players = playerName.map(::Player).also { player ->
        player.map {
            it.requestDeck(Deck.pop())
            it.requestDeck(Deck.pop())
        }
    }
    ResultView.printPreGame(players)
    return players
}
