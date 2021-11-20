package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.FirstDraw
import blackjack.domain.state.Stand
import blackjack.domain.state.State

class Dealer private constructor(
    override val name: String,
    override val state: State,
) : Gamer {

    override val cards: Cards
        get() = state.cards

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
        if (state.cards.getTotalScore() >= DEALER_DRAW_CONDITION) {
            return stand()
        }
        val currentState = draw(deck, state)
        return Dealer(name, currentState)
    }

    override fun draw(deck: Deck, state: State): State {
        val card = deck.takeOut()
        return state.draw(card)
    }

    override fun stand(): Dealer {
        return Dealer(name, Stand(cards))
    }

    override fun haveCards(): String {
        if (cards.value.size == DEALER_CARDS_OPEN_CONDITION) {
            return cards.value[DEALER_FIRST_CARD].toString()
        }
        return cards.haveCards()
    }

    companion object {
        private const val DEALER_DRAW_CONDITION = 17
        private const val DEALER_CARDS_OPEN_CONDITION = 2
        private const val DEALER_FIRST_CARD = 0
        private const val DEALER_NAME = "딜러"

        fun from(cards: Cards): Dealer {
            return Dealer(DEALER_NAME, FirstDraw(cards))
        }
    }
}
