package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands

sealed class State(open val hands: Hands) {

    abstract fun isFinished(): Boolean

    abstract fun score(): Score

    abstract fun draw(card: Card): State

    abstract fun stay(): State
}
