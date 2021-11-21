package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State
import blackjack.domain.state.result.Lose
import blackjack.domain.state.result.Push
import blackjack.domain.state.result.Win

class Player private constructor(
    name: String,
    state: State,
) : Gamer(name, state) {

    init {
        validateName(name)
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

    fun win(): Player {
        return Player(name, Win)
    }

    fun push(): Player {
        return Player(name, Push)
    }

    fun lose(): Player {
        return Player(name, Lose)
    }

    fun isBlackjack(): Boolean = cards.isBlackjack()

    fun isTwentyOne(): Boolean = cards.isTwentyOne()

    companion object {
        fun of(name: String, cards: Cards): Player {
            return Player(name, FirstDraw(cards))
        }

        fun init(name: String, state: State): Player {
            return Player(name, state)
        }
    }
}
