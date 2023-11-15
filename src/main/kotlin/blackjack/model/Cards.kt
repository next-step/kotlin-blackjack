package blackjack.model

class Cards(
    cards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    private val cards: List<Card> get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
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
            return Cards(mutableListOf())
        }
    }
}
