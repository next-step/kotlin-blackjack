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
    override val cards: Cards,
    override val state: State,
) : Gamer {

    init {
        if (name.isEmpty()) {
            throw InvalidPlayerNameException()
        }
    }

    override fun completeDeal(deck: Deck): Player {
        val completedFirstDraw = draw(deck, state)
        val completedDeal = draw(deck, completedFirstDraw)
        return Player(name, cards, completedDeal)
    }

    override fun play(deck: Deck): Player {
        if (state.currentCards().isBlackjack()) {
            return Player(name, cards, Blackjack(cards))
        }
        val currentState = draw(deck, state)
        return Player(name, cards, currentState)
    }

    override fun stand(): Player {
        return Player(name, cards, Stand(cards))
    }

    override fun draw(deck: Deck, state: State): State {
        val card = deck.takeOut()
        return state.draw(card)
    }

    override fun haveCards(): String = cards.haveCards()

    companion object {
        fun of(name: String, cards: Cards): Player {
            val state = FirstDraw(cards)
            return Player(name, cards, state)
        }
    }
}
