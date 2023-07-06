package blackjack.domain

class Player(val name: String) {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }
}
