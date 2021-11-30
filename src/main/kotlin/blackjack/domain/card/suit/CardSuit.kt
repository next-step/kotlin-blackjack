package blackjack.domain.card.suit

sealed class CardSuit(val suit: String) {
    override fun toString(): String = suit
    override fun equals(other: Any?): Boolean {
        if (other is CardSuit)
            return suit == other.suit
        return false
    }

    override fun hashCode(): Int {
        return suit.hashCode()
    }
}
