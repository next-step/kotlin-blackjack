package blackjack.core

class Player(val name: Name, val cards: Cards = Cards(), var status: Status = Status.HIT) {
    fun draw(card: Card) {
        cards += card
    }

    fun checkBust() {
        if (point() > Cards.MAX_POINT) {
            status = Status.BUSTED
        }
    }

    fun point(): Int = cards.point()

    fun getCardNames(): String = cards.getCardNames()
}
