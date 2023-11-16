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
        if (simpleSum + ALTERNATIVE_ACE_SCORE_DIFF > HIGHEST_SCORE) {
            return simpleSum
        }
        return simpleSum + ALTERNATIVE_ACE_SCORE_DIFF
    }

    fun count(): Int {
        return cards.size
    }

    companion object {
        private const val HIGHEST_SCORE: Int = 21
        private const val ALTERNATIVE_ACE_SCORE_DIFF: Int = 11 - 1
        
        fun init(): Cards {
            return Cards(mutableListOf())
        }
    }
}
