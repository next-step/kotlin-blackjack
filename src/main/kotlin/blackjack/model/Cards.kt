package blackjack.model

data class Cards(
    private val cards: MutableSet<Card> = mutableSetOf()
) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun totalScore(): Int {
        return cards.sumOf { it.rank.score }
    }

    fun present(): String {
        return cards.joinToString(separator = ", ") { "${it.rank.alias}${it.suit.alias}" }
    }

    fun count(): Int {
        return cards.size
    }

    companion object {
        fun init(): Cards {
            return Cards()
        }
    }
}
