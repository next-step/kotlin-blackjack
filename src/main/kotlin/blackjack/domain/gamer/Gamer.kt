package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.State
import blackjack.exception.InvalidPlayerNameException

abstract class Gamer(
    val name: String,
    val state: State,
) {
    val cards: Cards
        get() = state.cards

    fun validateName(name: String) {
        if (name.isEmpty()) {
            throw InvalidPlayerNameException()
        }
    }

    protected fun draw(deck: Deck, state: State): State {
        val card = deck.takeOut()
        return state.draw(card)
    }

    fun isStand(playable: Boolean): Boolean {
        return state.isStand(playable)
    }

    fun isFinished(): Boolean {
        return state.isFinished()
    }

    fun haveCards(): String {
        return cards.haveCards()
    }

    abstract fun prepare(deck: Deck): Gamer
    abstract fun play(deck: Deck): Gamer
    abstract fun stand(): Gamer

    companion object {
        const val BLACKJACK_SCORE = 21
    }
}
