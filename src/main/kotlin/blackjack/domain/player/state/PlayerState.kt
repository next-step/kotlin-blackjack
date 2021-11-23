package blackjack.domain.player.state

import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands

sealed class PlayerState(open val hands: Hands) {

    abstract fun match(other: PlayerState): MatchResult

    abstract fun score(): Score

    abstract fun isFinished(): Boolean

    abstract fun stay(): PlayerState

    abstract fun draw(card: Card): PlayerState
}
