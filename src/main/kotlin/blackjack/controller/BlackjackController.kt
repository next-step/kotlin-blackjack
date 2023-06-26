package blackjack.controller

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackController {
    private var wantStop = false

    fun run() {
        val players = prepareGame()
        BlackjackView.printPlayersCard(players)
        playGame(players)
        BlackjackView.printPlayersResult(players)
    }

    private fun prepareGame(): Players {
        val players = InputView.inputPlayers()

        val initialDraw = DEFAULT_INITIAL_DRAW
        repeat(initialDraw) {
            players.players.forEach { player -> player.drawCard() }
        }

        BlackjackView.printInitialTurn(players.players.map { it.name }, initialDraw)
        return players
    }

    private fun playGame(players: Players) {
        while (!wantStop) {
            players.players.forEach { playTurn(it) }
        }
    }

    private fun playTurn(player: Player) {
        while (player.canDraw() && BlackjackView.askDraw(player)) {
            player.drawCard()
            BlackjackView.printPlayerCard(player)
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
