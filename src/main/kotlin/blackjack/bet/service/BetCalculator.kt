package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.common.service.BlackJackDetermine

object BetCalculator {
    fun updateScores(winner: BlackJackDetermine.Winner, player: BetPlayer, dealer: BetDealer) {
        val bet = player.wallet().bet()
        val loseMoney = bet * -1
        when (winner) {
            BlackJackDetermine.Winner.DEALER_BUST -> {
                if (player.isPlayingRound()) settleRoundResult(dealer, bet, player, loseMoney)
            }
            BlackJackDetermine.Winner.PLAYER -> {
                settleRoundResult(dealer, loseMoney, player, bet)
            }
            BlackJackDetermine.Winner.DEALER -> {
                settleRoundResult(dealer, bet, player, loseMoney)
            }
            else -> {
                player.wallet().settle(0)
            }
        }
    }

    fun initialBlackjack(player: BetPlayer, dealer: BetDealer) {
        val bet = (player.wallet().bet() * 1.5).toInt()
        val loseMoney = bet * -1
        settleRoundResult(dealer, loseMoney, player, bet)
    }

    private fun settleRoundResult(
        dealer: BetDealer,
        bet: Int,
        player: BetPlayer,
        loseMoney: Int
    ) {
        dealer.wallet().settle(bet)
        player.wallet().settle(loseMoney)
    }
}
