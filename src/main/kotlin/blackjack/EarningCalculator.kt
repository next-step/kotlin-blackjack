package blackjack

import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Player
import kotlin.math.roundToInt

object EarningCalculator {
    fun calculateEarningByWinnerPlayer(dealer: Dealer, player: Player) {
        val twoCardsWithBlackjack = player.cards.getCardSize() == 2 && player.cards.isBlackJack()
        val playerBettingAmount = if (twoCardsWithBlackjack) {
            (player.bettingAmount * 1.5).roundToInt()
        } else {
            player.bettingAmount
        }

        player.calcEarningAmount(playerBettingAmount)
        dealer.calcEarningAmount(-playerBettingAmount)
    }

    fun calculateEarningByWinnerDealer(dealer: Dealer, player: Player) {
        val playerBettingAmount = player.bettingAmount
        player.calcEarningAmount(-playerBettingAmount)
        dealer.calcEarningAmount(playerBettingAmount)
    }
}
