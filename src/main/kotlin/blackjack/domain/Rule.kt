package blackjack.domain

import blackjack.domain.model.Dealer
import blackjack.domain.model.Player

object Rule {
    const val DEALER_MINIMUM_SCORE = 17
    const val BLACK_JACK = 21

    fun decisionWinner(dealer: Dealer, player: Player): Player? {
        val dealerSum = dealer.cards.sum
        val playerSum = player.cards.sum

        if (isOverBlackJack(dealerSum)) return recordPlayerWin(dealer, player)

        if (isOverBlackJack(playerSum)) return recordDealerWin(dealer, player)

        return when {
            dealerSum > playerSum -> recordDealerWin(dealer, player)
            dealerSum < playerSum -> recordPlayerWin(dealer, player)
            else -> null
        }
    }

    private fun isOverBlackJack(score: Int): Boolean = score > BLACK_JACK

    private fun recordPlayerWin(dealer: Dealer, player: Player): Player {
        dealer.recordLose()
        player.recordWin()
        return player
    }

    private fun recordDealerWin(dealer: Dealer, player: Player): Dealer {
        dealer.recordWin()
        player.recordLose()
        return dealer
    }
}
