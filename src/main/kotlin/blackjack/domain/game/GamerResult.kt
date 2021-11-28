package blackjack.domain.game

import blackjack.domain.game.Profit.Companion.sum
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer

data class GamerResult(val profit: Profit, val gamer: Gamer) {

    val name = gamer.name

    companion object {

        fun getDealerResultFromPlayers(dealer: Dealer, playerResults: List<GamerResult>): GamerResult {
            val dealerProfit = playerResults.map { it.profit }.sum().negative()
            return GamerResult(dealerProfit, dealer)
        }
    }
}
