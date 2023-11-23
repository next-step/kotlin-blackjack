package blackjack.domain

class Hand(cards: List<Card> = listOf()) {
    private val cards: MutableList<Card>

    init {
        this.cards = cards.toMutableList()
    }

    fun add(card: Card) = cards.add(card)

    fun size(): Int = cards.size

    fun toList(): List<Card> = cards.toList()

    fun sumOf(): Int {
        return cards.sumOf { it.num.score }
    }

    fun hasAce(): Boolean {
        return cards.any { it.num == CardNumberInfo.ACE }
    }
}
