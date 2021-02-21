package blackjack.domain.rate

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object PlayerWinRate : EarningRate {
    override fun isAssignable(dealer: Dealer, player: Player): Boolean {
        return dealerOnlyBust(player, dealer) || playerScoreWin(player, dealer) || allBlackJack(player, dealer)
    }

    private fun dealerOnlyBust(player: Player, dealer: Dealer) =
        (!player.isBust() && dealer.isBust())

    private fun playerScoreWin(player: Player, dealer: Dealer) =
        (!player.isBust() && !dealer.isBust() && player.score() > dealer.score())

    private fun allBlackJack(player: Player, dealer: Dealer) = player.isBlackJack() && dealer.isBlackJack()

    override fun getEarningRate(dealer: Dealer, player: Player): Double {
        require(isAssignable(dealer, player))
        return 1.0
    }
}
