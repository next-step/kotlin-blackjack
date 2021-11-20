package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.State
import blackjack.exception.InvalidPlayerNameException

interface Gamer {
    val name: String
    val cards: Cards
    val state: State

    fun validateName(name: String) {
        if (name.isEmpty()) {
            throw InvalidPlayerNameException()
        }
    }

    fun prepare(deck: Deck): Gamer

    fun play(deck: Deck): Gamer

    fun draw(deck: Deck, state: State): State

    fun stand(): Gamer

    fun haveCards(): String
}
