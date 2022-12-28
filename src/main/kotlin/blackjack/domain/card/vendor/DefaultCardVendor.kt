package blackjack.domain.card.vendor

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.CardHolder

class DefaultCardVendor : CardVendor {
    private val cards: Cards = Cards.ALL

    private var iterator: Iterator<Card> = cards.shuffled().iterator()

    override fun drawPlayerFirstTwoCards(): Cards = Cards(
        (1..CardHolder.INIT_CARD_COUNT)
            .map { drawCard() }
    )

    override fun drawCard(): Card {
        if (!iterator.hasNext()) {
            init()
        }

        return iterator.next()
    }

    private fun init() {
        iterator = cards.iterator()
    }
}
