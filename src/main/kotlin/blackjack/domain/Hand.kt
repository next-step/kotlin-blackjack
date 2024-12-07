package blackjack.domain

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    val score: Score
        get() = cards.fold(Score(0)) { acc, card -> card.score(acc) }
    val totalCards: List<Card>
        get() = cards.toList()
    val isBust: Boolean
        get() = score.isBust()

    fun add(otherCard: List<Card>) {
        cards.addAll(otherCard)
    }

    fun bustGap(): Int = (BUST_SCORE - score).abs()
}
