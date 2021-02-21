package blackjack.domain.rate

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

interface EarningRate {
    fun isAssignable(dealer: Dealer, player: Player): Boolean
    fun getEarningRate(dealer: Dealer, player: Player): Double
}
