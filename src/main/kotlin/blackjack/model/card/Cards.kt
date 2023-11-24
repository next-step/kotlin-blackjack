package blackjack.model.card

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
        if (simpleSum + ACE_BONUS_SCORE > HIGHEST_SCORE) {
            return simpleSum
        }
        return simpleSum + ACE_BONUS_SCORE
    }

    fun count(): Int {
        return cards.size
    }

    companion object {
        private const val HIGHEST_SCORE: Int = 21
        private const val ACE_BONUS_SCORE: Int = 10

        fun emptyCards(): Cards {
            return Cards(mutableListOf())
        }
    }
}
