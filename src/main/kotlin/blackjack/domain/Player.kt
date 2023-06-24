package blackjack.domain

class Player(private val name: String) {
    private var cards = mutableListOf<Card>()

    fun getName(): String {
        return name
    }

    fun getCards(): MutableList<Card> {
        return cards
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}
