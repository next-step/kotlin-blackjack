package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.GameCardsSet
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
        val gameCardsSet = GameCardsSet()
        val dealer = Dealer(gameCardsSet = gameCardsSet)
        val players = InputView.inputPlayers(gameCardsSet)
        initialTurn(dealer, players)

        return players
    }

    private fun initialTurn(dealer: Dealer, players: Players) {
        repeat(DEFAULT_INITIAL_DRAW) {
            dealer.drawCard()
            players.players.forEach { player -> player.drawCard() }
        }

        BlackjackView.printInitialTurn(dealer.name, players.players.map { it.name }, DEFAULT_INITIAL_DRAW)
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
