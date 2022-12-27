package blackjack.domain.card

import blackjack.domain.player.Player

class CardVendor {
    private val cards: Cards = Cards.ALL

    private var iterator: Iterator<Card> = cards.shuffled().iterator()

    fun drawPlayerFirstTwoCards(): Cards = Cards(
        (1..Player.INIT_CARD_COUNT)
            .map { drawCard() }
    )


    fun drawCard(): Card {
        if (!iterator.hasNext()) {
            init()
        }

        return iterator.next()
    }

    private fun init() {
        iterator = cards.iterator()
    }

}
