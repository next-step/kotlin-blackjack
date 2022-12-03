package model

class Cards(val cards: MutableList<Card> = mutableListOf()) {
    fun count(): Int = cards.size
    fun add(card: Card) {
        cards.add(card)
    }

    fun hit() = cards.removeFirst()
}
