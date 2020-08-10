package blackjack.controller

import blackjack.model.Card
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = registerPlayers()

    drawCard(players)
    showResult(players)
}

private fun showResult(players: List<Player>) {
    players.map { ResultView.printResult(it, it.calculateRank()) }
}

private fun drawCard(players: List<Player>) {
    players.map {
        while (isContinueDraw(it)) {
            it.requestCard(Card.pop())
            ResultView.printPlayerHaveCard(it)
        }
    }
}

private fun isContinueDraw(player: Player) =
    player.calculateRank() < Player.MAX_RANK && InputView.requestOneOfCard(player) == "y"

private fun registerPlayers(): List<Player> {
    val playerName = InputView.requestPlayerNames()
    val players = playerName.map(::Player).also { player ->
        player.map {
            it.requestCard(Card.pop())
            it.requestCard(Card.pop())
        }
    }
    ResultView.printPreGame(players)
    return players
}
