package baclkjack.domain.play

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck
import baclkjack.domain.play.GameState.Companion.ofGameState

open class Player(val name: String) {

    private val cards: Cards = Cards()

    var draw: ((String) -> Boolean)? = null

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

    fun score(): Int = cards.score()

    fun cards(): List<Card> = cards.cards

    fun finish(): Boolean = burst() || blackJack()

    open fun isDraw(): Boolean = draw?.invoke(name) == true

    fun result(player: Player): GameState = ofGameState(player)

    companion object {
        const val FIRST_DRAW = 2
    }
}
