package game.blackjack.domain

class Cards(
    private val cards: MutableList<Card>
) {
    constructor() : this(mutableListOf())

    fun get(): List<Card> = cards.toList()

    fun score(): Score {
        val denominations = cards.map { it.denomination }
        return denominations
            .sumOf { it.score }
            .sumIfSoftHand(denominations.contains(Denomination.ACE))
    }

    fun isBust(): Boolean = score().isBust()

    fun isBlackJack(): Boolean = score().isBlackJack() && cards.size == 2

    fun add(card: Card) {
        cards.add(card)
    }

    fun add(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun size(): Int = cards.size
}
