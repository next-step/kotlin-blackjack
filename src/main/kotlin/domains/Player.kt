package domains

class Player(val name: String, val cards: Cards = Cards()) {

    fun addCard(card: Card) {
        cards.addCard(card)
    }
}
