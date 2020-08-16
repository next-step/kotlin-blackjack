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
        return cards.first()
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun score(): Int {
        var sum = cards.sumBy { it.denomination.score() }
        cards.filter { it.denomination.isAce() }.forEach {
            sum = it.denomination.scoreWithAce(sum)
        }
        return sum
    }

    operator fun get(index: Int): Card {
        return cards[index]
    }

    override fun toString(): String {
        return "${cards.joinToString { it.toString() }}"
    }
}
