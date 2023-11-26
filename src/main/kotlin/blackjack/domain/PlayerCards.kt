package blackjack.domain

class PlayerCards(
    values: List<Card> = emptyList()
) {
    var values: List<Card> = values
        private set

    override fun toString(): String {
        return values.joinToString(",  ")
    }

    fun add(vararg cards: Card) {
        values = values + cards
    }
}
