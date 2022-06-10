package blackjack.domain

class Player(val name: String) {
    val cards = mutableListOf<Card>()
    fun takeCard(card: Card) {
        cards.add(card)
    }
}
