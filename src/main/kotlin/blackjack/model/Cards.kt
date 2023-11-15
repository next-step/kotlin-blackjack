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
        if (this.isContainsAce()) {
            return scoreWithAce()
        }
        return simpleSumOfScore()
    }

    private fun simpleSumOfScore(): Int {
        return cards.sumOf { it.cardRank.score }
    }

    private fun isContainsAce(): Boolean {
        return this.cards
            .any { it.cardRank == CardRank.ACE }
    }

    private fun scoreWithAce(): Int {
        val simpleSum = simpleSumOfScore()
        if (simpleSum + 10 > 21) {
            return simpleSum
        }
        return simpleSum + 10
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
