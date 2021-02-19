package blackjack.domain.rate

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object BlackJackRate : EarningRate {
    override fun isAssignable(dealer: Dealer, player: Player): Boolean {
        return playerOnlyBlackJack(dealer, player)
    }

    private fun playerOnlyBlackJack(dealer: Dealer, player: Player) =
        !dealer.isBlackJack() && player.isBlackJack()

    override fun getEarningRate(dealer: Dealer, player: Player): Double {
        require(isAssignable(dealer, player))
        return 1.5
    }
}
