package blackjack.domain

class PlayerCards {
    val cards: MutableList<Card> = mutableListOf()

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }

    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        const val MAXIMUM_SUM_OF_CARD_NUMBERS = 21
    }
}
