package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State

class Dealer private constructor(
    name: String,
    state: State,
) : Gamer(name, state) {

    init {
        validateName(name)
    }

    override fun prepare(deck: Deck): Dealer {
        val completedFirstDraw = draw(deck, state)
        val completedSecondDraw = draw(deck, completedFirstDraw)
        return Dealer(name, completedSecondDraw)
    }

    override fun play(deck: Deck): Dealer {
        if (state.cards.isBlackjack()) {
            return Dealer(name, Blackjack(cards))
        }
        val currentState = draw(deck, state)
        return Dealer(name, currentState)
    }

    override fun stand(): Dealer {
        return Dealer(name, Stand(cards))
    }

    fun meetConditions(currentScore: Int): Dealer {
        if (currentScore == BLACKJACK_SCORE) {
            return Dealer(name, Blackjack(cards))
        }
        if (currentScore > BLACKJACK_SCORE) {
            return Dealer(name, Bust(cards))
        }
        return Dealer(name, Stand(cards))
    }

    fun preparedCurrentScore(): Int {
        return cards.getTotalScore()
    }

    fun currentScore(): Int {
        return cards.getTotalScore()
    }

    companion object {
        private const val DEALER_NAME = "딜러"

        fun from(cards: Cards): Dealer {
            return Dealer(DEALER_NAME, FirstDraw(cards))
        }

        fun init(name: String, state: State): Dealer {
            return Dealer(name, state)
        }
    }
}
