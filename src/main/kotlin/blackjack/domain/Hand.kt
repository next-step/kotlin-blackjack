package blackjack.domain

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    val score: Int
        get() = cards.sortedBy { if (it.rank.isAce()) 1 else 0 }.fold(0) { acc, card -> card.score(acc) }
    val totalCards: List<Card>
        get() = cards.toList()
    val isBust: Boolean
        get() = score > 21

    fun add(otherCard: List<Card>) {
        cards.addAll(otherCard)
    }
}
