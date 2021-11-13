package blackjack.domain

class Hand(cards: List<Card>) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card> = _cards

    val score: Score
        get() {
            val score = sumOfScore()
            if (hasAce()) {
                return score.aceRevised
            }
            return score
        }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun canHit() = score.canHit()

    fun isBlackJack() = score.isBlackJack()

    fun isBust() = score.isBust()

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
