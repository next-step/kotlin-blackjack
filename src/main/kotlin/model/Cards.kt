package model

class Cards(val cards: MutableList<Card> = mutableListOf()) {
    fun count(): Int = cards.size
    fun add(card: Card) {
        cards.add(card)
    }

    fun pick() = cards.removeFirst()
    override fun toString(): String {
        return cards.joinToString(", ") { it.pokerNumber.value + it.pokerShape.value }
    }
}

