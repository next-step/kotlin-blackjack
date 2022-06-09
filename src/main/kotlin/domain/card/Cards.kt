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

    fun remove(card: Card) {
        cards.remove(card)
    }

    fun draw(): Card {
        return cards.first()
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun score(): Int {
        var sum = cards.sumOf { card -> card.symbol.score }
        return cards.filter { card -> card.symbol.isAce() }
            .fold(sum) { acc, aceCard -> aceCard.symbol.decideAceScore(acc) }
    }

    operator fun get(index: Int): Card {
        return cards[index]
    }
}