package blackjack.domain

class Player(val name: String) {
    private val cards = mutableListOf<Card>()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun addCards(drawCards: List<Card>) {
        drawCards.forEach {
            cards.add(it)
        }
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }
}
