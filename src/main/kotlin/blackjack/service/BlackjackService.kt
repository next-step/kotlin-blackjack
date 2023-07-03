package blackjack.service

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.GameCardsSet
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.forEachPlayer
import blackjack.view.BlackjackView

class BlackjackService {
    private val gameCardsSet = GameCardsSet()
    private var wantStop = false

    fun initialTurn(dealer: Dealer, players: Players) {
        repeat(DEFAULT_INITIAL_DRAW) {
            dealer.hit(drawCard())
            players.forEachPlayer { player -> player.hit(drawCard()) }
        }
    }

    fun playGame(dealer: Dealer, players: Players) {
        while (!wantStop) {
            players.forEachPlayer { hit(it) }
        }

        dealerExtraHit(dealer)
    }

    private fun dealerExtraHit(dealer: Dealer) {
        if (dealer.sumOfMyCards() <= Dealer.DEALER_UNDER_NUMBER) {
            dealer.hit(drawCard())
            BlackjackView.printDealerExtraHit(dealer.name)
        }
    }

    private fun drawCard(): Card = gameCardsSet.drawRandomCard()

    private fun hit(player: Player) {
        while (goNext(player)) {
            player.hit(drawCard())
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
