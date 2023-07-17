package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Score

interface State {
    fun draw(card: Card): State {
        throw IllegalStateException("This state can not draw, state : ${this.javaClass}")
    }

    fun score(): Score {
        throw IllegalStateException("This state can not get score, state : ${this.javaClass}")
    }

    fun stay(): State {
        throw IllegalStateException("This state can not stay, state : ${this.javaClass}")
    }

    fun currentDeck(): Deck {
        throw IllegalStateException("This state can not get a deck, state : ${this.javaClass}")
    }
}
