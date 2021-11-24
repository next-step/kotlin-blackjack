package blackjack.domain.card

class Cards(cards: List<Card>) {

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

    fun take(n: Int): List<Card> {
        return cards.take(n)
    }

    fun isBlackJack() = (score.canBlackJack() && cards.size == BLACK_JACK_SIZE)

    fun isBust() = score.isBust()

    private fun hasAce() = cards.any { it.isAce() }

    private fun sumOfScore(): Score {
        return cards.map { it.score }.sum()
    }

    companion object {

        private const val BLACK_JACK_SIZE = 2

        fun createEmpty(): Cards {
            return Cards(emptyList())
        }
    }
}
