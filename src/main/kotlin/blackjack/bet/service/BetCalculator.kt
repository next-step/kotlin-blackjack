package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.common.service.BlackJackDetermine

object BetCalculator {
    fun updateScores(winner: BlackJackDetermine.Winner, player: BetPlayer, dealer: BetDealer) {
        val allIn = player.wallet().balance()
        val loss = allIn * -1
        when (winner) {
            BlackJackDetermine.Winner.DEALER_BUST -> {
                if (player.isPlaying()) {
                    playerWin(player, allIn, dealer, loss)
                }
            }
            BlackJackDetermine.Winner.PLAYER -> {
                playerWin(player, allIn, dealer, loss)

            }
            BlackJackDetermine.Winner.DEALER -> {
                dealer.wallet().settle(allIn)
                player.wallet().settle(loss)
            }
            else -> {
                player.wallet().settle(0)
            }
        }
    }

    fun initialBlackjack(player: BetPlayer, dealer: BetDealer) {
        val balance = player.wallet().balance()
        val blackJackPit = (balance * 0.5).toInt()
        val loss = (balance + blackJackPit) * -1
        playerWin(player, blackJackPit, dealer, loss)
    }

    private fun playerWin(
        player: BetPlayer,
        allIn: Int,
        dealer: BetDealer,
        loss: Int
    ) {
        player.wallet().settle(allIn)
        dealer.wallet().settle(loss)
    }
}
