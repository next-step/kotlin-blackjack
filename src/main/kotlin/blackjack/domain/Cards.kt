package blackjack.domain

class Cards(
    values: List<Card> = emptyList()
) {
    var values: List<Card> = values
        private set
    val size
        get() = values.size

    override fun toString(): String {
        return values.joinToString(",  ")
    }

    fun add(vararg card: Card) {
        values = values + card
    }

    fun isBust(): Boolean {
        return toScore() > Score.BLACKJACK
    }
    fun isBlackjack(): Boolean {
        return toScore() == Score.BLACKJACK
    }
}
