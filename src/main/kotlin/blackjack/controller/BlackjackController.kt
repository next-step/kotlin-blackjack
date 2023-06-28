package blackjack.controller

import blackjack.domain.Cards.Companion.BLACK_JACK_SCORE
import blackjack.domain.Dealer
import blackjack.domain.Dealer.Companion.DEALER_INITIAL_TURN_LIMIT
import blackjack.domain.GameCardsSet
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackController {
    private var wantStop = false

    fun run() {
        val dealerAndPlayers = prepareGame()
        val dealer = dealerAndPlayers.first
        val players = dealerAndPlayers.second

        playGame(dealer, players)
        BlackjackView.printPlayersResult(listOf(dealer).plus(players.players))
    }

    private fun prepareGame(): Pair<Dealer, Players> {
        val gameCardsSet = GameCardsSet()
        val dealer = Dealer(gameCardsSet = gameCardsSet)
        val players = InputView.inputPlayers(gameCardsSet)

        initialTurn(dealer, players)
        return Pair(dealer, players)
    }

    private fun initialTurn(dealer: Dealer, players: Players) {
        repeat(DEFAULT_INITIAL_DRAW) {
            dealer.drawCard()
            players.players.forEach { player -> player.drawCard() }
        }

        BlackjackView.printInitialTurn(dealer.name, players.players.map { it.name }, DEFAULT_INITIAL_DRAW)
        BlackjackView.printDealerCard(dealer)
        BlackjackView.printPlayersCard(players)
    }

    private fun playGame(dealer: Dealer, players: Players) {
        while (!wantStop) {
            players.players.forEach { playTurn(it) }
        }

        if (dealer.sumOfMyCards() <= DEALER_INITIAL_TURN_LIMIT) {
            dealer.drawCard()
            BlackjackView.printDealerExtraHit(dealer.name)
        }

        if (dealer.sumOfMyCards() > BLACK_JACK_SCORE) {
            wantStop = true
            return
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
