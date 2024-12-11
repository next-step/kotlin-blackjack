package blackjack.domain

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    val score: Score
        get() = cards.fold(Score.ZERO) { acc, card -> card.score(acc) }
    val totalCards: List<Card>
        get() = cards.toList()
    val isBust: Boolean
        get() = score.isBust()

    fun add(otherCard: List<Card>) {
        cards.addAll(otherCard)
    }

    fun bustGap(): Int = (BUST_SCORE - score).abs()

    fun isBlackJack(): Boolean {
        return cards.size != BLACKJACK_HAND_SIZE && score == BLACKJACK_SCORE
    }

    companion object {
        const val BLACKJACK_HAND_SIZE = 2
        val BLACKJACK_SCORE = Score(21)
    }
}
