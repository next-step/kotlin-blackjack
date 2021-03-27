package blackjack.domain.player

import blackjack.domain.Card
import blackjack.domain.state.State

interface Gamer {

    val name: String
    val state: State

    fun isTakeable(): Boolean
    fun takeCard(card: Card)
}
