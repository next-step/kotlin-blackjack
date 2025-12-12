package domain

class Dealer(val cards: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        cards.add(card)
    }
}
