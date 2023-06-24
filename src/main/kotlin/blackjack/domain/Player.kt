package blackjack.domain

class Player(val name: String) {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
