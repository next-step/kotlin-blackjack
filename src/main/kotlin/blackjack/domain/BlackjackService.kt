package blackjack.domain

import blackjack.view.BlackjackView
import blackjack.view.InputView

class BlackjackService {
    private var wantStop = false

    fun prepareGame(): Pair<Dealer, Players> {
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
    }

    fun playGame(dealer: Dealer, players: Players) {
        while (!wantStop) {
            players.players.forEach { hit(it) }
        }

        if (dealer.sumOfMyCards() <= Dealer.DEALER_UNDER_NUMBER) {
            dealer.hit()
            BlackjackView.printDealerExtraHit(dealer.name)
        }
    }

    private fun hit(player: Player) {
        while (goNext(player)) {
            player.hit()
            BlackjackView.printPlayerCard(player)
        }
        wantStop = true
    }

    private fun goNext(player: Player): Boolean {
        if (!player.state.canDraw) {
            return false
        }

        val wantDraw = BlackjackView.askDraw(player)
        if (!wantDraw) {
            player.stand()
        }
        return wantDraw
    }

    companion object {
        const val DEFAULT_INITIAL_DRAW: Int = 2
    }
}
