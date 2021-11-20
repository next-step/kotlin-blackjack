package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.State

interface Gamer {
    val name: String
    val cards: Cards
    val state: State

    fun draw(deck: Deck, state: State): State

    fun stand(): Gamer

    fun play(deck: Deck): Gamer

    fun prepare(deck: Deck): Gamer

    fun haveCards(): String
}
