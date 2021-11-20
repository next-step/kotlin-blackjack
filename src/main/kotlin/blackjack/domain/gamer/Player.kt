package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State
import blackjack.exception.InvalidPlayerNameException

class Player private constructor(
    override val name: String,
    override val state: State,
) : Gamer {

    override val cards: Cards
        get() = state.cards

    init {
        if (name.isEmpty()) {
            throw InvalidPlayerNameException()
        }
    }

    override fun prepare(deck: Deck): Player {
        val completedFirstDraw = draw(deck, state)
        val completedSecondDraw = draw(deck, completedFirstDraw)
        return Player(name, completedSecondDraw)
    }

    override fun play(deck: Deck): Player {
        if (state.cards.isBlackjack()) {
            return Player(name, Blackjack(cards))
        }
        val currentState = draw(deck, state)
        return Player(name, currentState)
    }

    override fun stand(): Player {
        return Player(name, Stand(cards))
    }

    override fun draw(deck: Deck, state: State): State {
        val card = deck.takeOut()
        return state.draw(card)
    }

    fun isStand(playable: Boolean): Boolean {
        return state.isStand(playable)
    }

    fun isFinished(): Boolean {
        return state.isFinished()
    }

    override fun haveCards(): String = cards.haveCards()

    companion object {
        fun of(name: String, cards: Cards): Player {
            return Player(name, FirstDraw(cards))
        }
    }
}
