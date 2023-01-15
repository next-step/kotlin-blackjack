package blackjack.domains

class Player(val name: String, val cards: Cards = Cards()) {

    fun startGame(cards: Cards) {
        cards.values.forEach { drawCard(it) }
    }

    fun drawCard(card: Card) {
        cards.addCard(card)
    }
}
