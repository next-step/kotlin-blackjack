package model

class Cards(val cards: MutableList<Card> = mutableListOf()) {
    fun count(): Int = cards.size
}
