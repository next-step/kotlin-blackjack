package blackjack.model

class Cards(
    cards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun totalScore(): Int {
        return cards.sumOf { it.cardRank.score }
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
