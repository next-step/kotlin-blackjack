package blackjack.domain

class Cards(val list: MutableList<Card> = mutableListOf()) {
    val count: Int = list.size
    fun add(card: Card) {
        list.add(card)
    }
}
