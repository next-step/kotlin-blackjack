package blackjack.domain.rate

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object PlayerLostRate : EarningRate {
    override fun isAssignable(dealer: Dealer, player: Player): Boolean {
        return player.isBust() || dealerScoreWin(dealer, player)
    }

    private fun dealerScoreWin(dealer: Dealer, player: Player) =
        (!dealer.isBust() && dealer.score() > player.score())

    override fun getEarningRate(dealer: Dealer, player: Player): Double {
        require(isAssignable(dealer, player))
        return -1.0
    }
}
