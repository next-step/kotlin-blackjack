package domain.card

class Cards(cards: List<Card>) {
    private val cards = cards.toMutableList()

    override fun toString(): String {
        return cards.joinToString(" ") { card -> card.toString() }
    }

    fun add(card: Card) {
        if (!cards.contains(card)) {
            cards.add(card)
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun draw(): Card {
        return cards.first().apply { cards.remove(this) }
    }

    fun score(): Int {
        val sum = cards.sumOf { card -> card.symbol.score }
        return cards.filter { card -> card.symbol.isAce() }
            .fold(sum) { acc, aceCard -> aceCard.symbol.reCalculateAce(acc) }
    }

    operator fun get(index: Int): Card {
        return cards[index]
    }
}