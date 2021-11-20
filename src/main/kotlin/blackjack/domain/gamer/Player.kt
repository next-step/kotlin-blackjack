package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State
import blackjack.exception.InvalidPlayerNameException

class Player(
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

    override fun completeDeal(deck: Deck): Player {
        val completedFirstDraw = draw(deck, state)
        val completedDeal = draw(deck, completedFirstDraw)
        return Player(name, completedDeal)
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

    override fun haveCards(): String = cards.haveCards()

    companion object {
        fun of(name: String, cards: Cards): Player {
            return Player(name, FirstDraw(cards))
        }
    }
}
