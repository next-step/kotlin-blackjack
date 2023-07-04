package blackjack.domain

class User(
    val name: String,
    var cards: Cards,
    var isDeckComplete: Boolean = false
) {

    fun cardSize(): Int {
        return cards.size
    }

    fun addCard(card: Card) {
        cards = cards.addCard(card)
    }

    fun cardValues(): Int {
        return cards.value()
    }

    fun deckComplete() {
        isDeckComplete = true
    }
}
