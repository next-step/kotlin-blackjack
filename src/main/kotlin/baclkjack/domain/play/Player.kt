package baclkjack.domain.play

import baclkjack.domain.card.Deck

class Player(val name: String) {

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

    fun blackJack(): Boolean = cards.isBlackJack()

    fun result(): Int = cards.score()

    fun cards() = cards.cards

    companion object {
        const val FIRST_DRAW = 2
    }
}
