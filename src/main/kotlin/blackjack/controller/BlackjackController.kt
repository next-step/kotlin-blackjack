package blackjack.controller

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackController {
    private var wantStop = false
    private val inputView = InputView()
    private val blackjackView = BlackjackView()

    fun run() {
        val players = prepareGame()
        blackjackView.printPlayersCard(players)
        playGame(players)
        blackjackView.printPlayersResult(players)
    }

    private fun prepareGame(): Players {
        val players = inputView.inputPlayers()

        val initialDraw = DEFAULT_INITIAL_DRAW
        repeat(initialDraw) {
            players.players.forEach { player -> player.drawCard() }
        }

        blackjackView.printInitialTurn(players.players.map { it.name }, initialDraw)
        return players
    }

    private fun playGame(players: Players) {
        while (!wantStop) {
            players.players.forEach { playTurn(it) }
        }
    }

    private fun playTurn(player: Player) {
        while (player.canDraw() && blackjackView.askDraw(player)) {
            player.drawCard()
            blackjackView.printPlayerCard(player)
        }
        wantStop = true
    }

    companion object {
        private const val DEFAULT_INITIAL_DRAW: Int = 2
    }
}

fun main() {
    BlackjackController().run()
}
