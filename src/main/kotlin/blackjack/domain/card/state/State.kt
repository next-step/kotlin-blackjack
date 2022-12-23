package blackjack.domain.card.state

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

sealed class State {
    abstract val cards: PlayingCards

    abstract fun draw(playingCard: PlayingCard): State

    abstract fun stay(): State

    abstract fun isFinished(): Boolean
}
