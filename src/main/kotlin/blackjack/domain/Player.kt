package blackjack.domain

class Player(val name: String) {
    private val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalValue(): Int {
        return cards.getTotalValue()
    }

    fun getCards(): List<Card> {
        return cards.asList()
    }
}
