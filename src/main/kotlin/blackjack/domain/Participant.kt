package blackjack.domain

abstract class Player(val name: String, val myCards: Cards = Cards()) {

    val totalScore: Int
        get() = myCards.totalScore

    abstract fun canDraw(): Boolean

    fun draw(cardDeck: CardDeck): Card {
        return cardDeck.draw()
    }

    fun receive(card: Card): Boolean = myCards.add(card)
}

class Dealer(name: String, myCards: Cards = Cards()) : Player(name, myCards) {
    override fun canDraw(): Boolean {
        return totalScore <= MIN_DRAW_SCORE
    }
}

class Gamer(name: String, myCards: Cards = Cards()) : Player(name, myCards) {
    override fun canDraw(): Boolean {
        return totalScore <= BLACK_JACK
    }
}
