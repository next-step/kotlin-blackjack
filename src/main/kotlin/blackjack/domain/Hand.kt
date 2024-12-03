package blackjack.domain

const val BUST_SCORE = 21

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    val score: Int
        get() = cards.fold(0) { acc, card -> card.score(acc) }
    val totalCards: List<Card>
        get() = cards.toList()
    val isBust: Boolean
        get() = score >= BUST_SCORE

    fun add(otherCard: List<Card>) {
        cards.addAll(otherCard)
    }
}
