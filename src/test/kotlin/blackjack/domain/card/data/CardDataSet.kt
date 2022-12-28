package blackjack.domain.card.data

import blackjack.domain.card.Card
import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.card.vendor.DefaultCardVendor

class CardDataSet {
    companion object {
        private val cardVendor: CardVendor = DefaultCardVendor()

        fun testData(): Card = cardVendor.drawCard()
    }
}
