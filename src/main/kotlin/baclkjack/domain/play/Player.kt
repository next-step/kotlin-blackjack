package baclkjack.domain.play

import baclkjack.domain.card.Deck

class Player(private val _name: String) {

    val name: String get() = _name

    private val cards: Cards = Cards()

    fun start(deck: Deck) {
        repeat(FIRST_DRAW) {
            cards.add(deck.draw())
        }
    }

    fun hit(deck: Deck) {
        cards.add(deck.draw())
    }

    fun burst(): Boolean = cards.isBurst()

    fun result(): Int = cards.score()

    fun cardList() = cards.toString()

    companion object {
        const val FIRST_DRAW = 2
    }

}
