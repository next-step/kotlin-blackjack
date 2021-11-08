package blackjack.domain

class Hand(cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card> = _cards

    fun add(card: Card) {
        _cards.add(card)
    }

    val score: Score
        get() {
            val score = sumOfScore()
            if (hasAce()) {
                return score.aceRevised
            }
            return score
        }

    private fun hasAce() = cards.any { it.isAce() }

    private fun sumOfScore(): Score {
        return cards.map { it.score }.sum()
    }

    companion object {
        fun createEmpty(): Hand {
            return Hand(emptyList())
        }
    }
}
