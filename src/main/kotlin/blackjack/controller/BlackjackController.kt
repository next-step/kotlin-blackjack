package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Dealer.Companion.DEALER_UNDER_NUMBER
import blackjack.domain.GameCardsSet
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.service.GameResultService
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

        val gameResult = GameResultService().allResult(dealer, players)
        BlackjackView.printGameResult(gameResult)
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
            dealer.hit()
            players.players.forEach { player -> player.hit() }
        }

        BlackjackView.printInitialTurn(dealer.name, players.players.map { it.name }, DEFAULT_INITIAL_DRAW)
        BlackjackView.printDealerFirstCard(dealer)
        BlackjackView.printPlayersCard(players)
    }

    private fun playGame(dealer: Dealer, players: Players) {
        while (!wantStop) {
            players.players.forEach { hit(it) }
        }

        if (dealer.sumOfMyCards() <= DEALER_UNDER_NUMBER) {
            dealer.hit()
            BlackjackView.printDealerExtraHit(dealer.name)
        }

        dealer.findStateBySum()
    }

    private fun hit(player: Player) {
        while (goNext(player)) {
            player.hit()
            BlackjackView.printPlayerCard(player)
        }
        wantStop = true
    }

    private fun goNext(player: Player): Boolean {
        val canDraw = player.canDraw()
        if (!canDraw) {
            player.findStateBySum()
            return false
        }

        val wantDraw = BlackjackView.askDraw(player)
        if (!wantDraw) {
            player.stand()
        }
        return wantDraw
    }

    companion object {
        private const val DEFAULT_INITIAL_DRAW: Int = 2
    }
}

fun main() {
    BlackjackController().run()
}
