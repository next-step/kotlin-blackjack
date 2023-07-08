package blackjack.domain

class Player(val name: String) {
    val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }
}
