package baclkjack.domain.play

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck
import baclkjack.domain.play.GameState.Companion.ofGameState

class Player(override val name: String) : User {

    private val cards: Cards = Cards()

    var draw: ((String) -> Boolean)? = null

    override fun start(deck: Deck) {
        repeat(FIRST_DRAW) {
            cards.add(deck.draw())
        }
    }

    override fun hit(deck: Deck) {
        cards.add(deck.draw())
    }

    override fun burst(): Boolean = cards.isBurst()

    override fun blackJack(): Boolean = cards.isBlackJack()

    override fun score(): Int = cards.score()

    override fun cards(): List<Card> = cards.cards

    override fun finish(): Boolean = burst() || blackJack()

    override fun isDraw(): Boolean = draw?.invoke(name) == true
    fun result(player: User): GameState = ofGameState(player)

    companion object {
        const val FIRST_DRAW = 2
    }
}
