package blackjack.model

data class Cards(
    private val cards: MutableSet<Card> = mutableSetOf()
) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun totalScore(): Int {
        return cards.sumOf { it.cardRank.score }
    }

    fun present(): String {
        return cards.joinToString(separator = ", ") { "${it.cardRank.alias}${it.suit.alias}" }
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
