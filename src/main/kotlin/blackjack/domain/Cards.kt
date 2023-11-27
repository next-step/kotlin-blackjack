package blackjack.domain

class Cards(
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

    fun isBust(): Boolean {
        return toScore() > Score.BLACKJACK
    }
    fun isBlackjack(): Boolean {
        return toScore() == Score.BLACKJACK
    }
}
