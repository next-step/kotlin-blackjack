package blackjack.domain.card.vendor

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.toCards

class DefaultCardVendor : CardVendor {
    private val cards: Cards = Cards.ALL

    private var iterator: Iterator<Card> = cards.shuffled().iterator()

    override fun drawCard(): Card {
        if (!iterator.hasNext()) {
            init()
        }

        return iterator.next()
    }

    override fun drawCards(count: Int): Cards {
        require(count >= 0) {
            "count should be greater or equal to 0"
        }

        return (1..count).map { drawCard() }.toCards()
    }

    private fun init() {
        iterator = cards.iterator()
    }
}
