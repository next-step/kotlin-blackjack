package blackjack

data class Cards(val cards: List<Card>) {
    override fun toString(): String {
        return cards.joinToString(", ")
    }

    fun isBust(): Boolean {
        return sum() >= BLACKJACK
    }

    fun sum() = cards.sumOf { it.number.score }

    fun addWith(card: Card): Cards {
        return Cards(cards + card)
    }

    companion object {
        private const val BLACKJACK = 21
    }
}