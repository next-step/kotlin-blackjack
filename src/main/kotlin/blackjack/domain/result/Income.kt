package blackjack.domain.result

import blackjack.domain.participantion.Dealer
import blackjack.domain.participantion.Participant
import blackjack.domain.participantion.Price

object Income {

    fun settle(winners: Winners, losers: Losers, dealer: Dealer): List<Participant> {
        val dealerIncomePrice = settleDealerPrice(winners, losers, dealer)
        dealer.earn(dealerIncomePrice)

        return listOf(dealer) + winners.players + losers.settle()
    }

    private fun settleDealerPrice(winners: Winners, losers: Losers, dealer: Dealer): Price {
        if (dealer.isBust()) {
            return Price(dealer.priceAmount)
        }

        val losersTotalPrice = losers.getTotalPrice()
        val winnersTotalPrice = winners.getTotalPrice()
        val blackJackIncomePrice = winners.blackJackIncome(dealer)

        val dealerAmount = dealer.priceAmount
            .plus(losersTotalPrice.amount)
            .minus(winnersTotalPrice.amount)
            .minus(blackJackIncomePrice.amount)

        return Price(dealerAmount)
    }
}
