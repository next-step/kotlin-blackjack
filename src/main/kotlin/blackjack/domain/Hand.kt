package blackjack.domain

class Hand(private val cards: MutableList<Card> = mutableListOf()) {
    val score: Int
        get() = cards.fold(0) { acc, card -> card.score(acc) }
    val totalCards: List<Card>
        get() = cards.toList()
    val isBust: Boolean
        get() = score > 21

    fun add(card: List<Card>) {
        cards.addAll(card)
    }
}
