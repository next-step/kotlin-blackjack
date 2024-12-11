package blackjack.domain

open class Participant {
    private val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalValue(): Int {
        return cards.getTotalValue()
    }

    fun getCards(): List<Card> {
        return cards.asList()
    }

    fun isBlackjack(): Boolean {
        return getCards().size == INITIAL_DRAW_COUNT && getTotalValue() == BLACKJACK
    }

    companion object {
        private const val BLACKJACK = 21
        private const val INITIAL_DRAW_COUNT = 2
    }
}
