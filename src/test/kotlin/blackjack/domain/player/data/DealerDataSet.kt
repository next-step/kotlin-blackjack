package blackjack.domain.player.data

import blackjack.domain.card.data.CardsDataSet
import blackjack.domain.card.vendor.DefaultCardVendor
import blackjack.domain.player.Dealer

class DealerDataSet {
    companion object {
        fun testDataWithTwoCards(score: Int): Dealer {
            require(score >= 2) {
                "score should be greater or equal than 2"
            }

            return Dealer("username", CardsDataSet.testDataWithTwoCards(score), DefaultCardVendor())
        }
    }
}
