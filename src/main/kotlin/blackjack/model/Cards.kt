package blackjack.model

class Cards(cards: List<Card>) {
    private val cards = cards.toMutableList()

    fun add(card: Card) {
        if (!cards.contains(card)) {
            cards.add(card)
        }
    }

    fun remove(card: Card) {
        cards.remove(card)
    }

    fun draw(): Card {
        return cards[0]
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun score(): Int {
        var sum = cards.sumBy { it.denomination.score }
        if (this.containsAce()) {
            sum = Denomination.calculate(sum)
        }
        return sum
    }

    private fun containsAce(): Boolean {
        return cards.count { it.denomination == Denomination.ACE } > 0
    }

    operator fun get(index: Int): Card {
        return cards[index]
    }

    override fun toString(): String {
        return "${cards.joinToString { it.toString() }}"
    }
}
