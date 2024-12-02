package blackjack

data class Cards(val cards: List<Card>) {
    override fun toString(): String {
        return cards.joinToString(", ")
    }
}