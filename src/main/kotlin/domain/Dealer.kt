package domain

class Dealer(val cards: MutableList<Card> = mutableListOf()) {
    fun receiveCard(card: Card) {
        cards.add(card)
    }
}
