package blackjack.core

class Player(val name: Name, val cards: Cards = Cards()) {
    fun draw(card: Card) {
        cards += card
    }

    fun point(): Int = cards.point()

    fun getCardNames(): String = cards.getCardNames()
}
