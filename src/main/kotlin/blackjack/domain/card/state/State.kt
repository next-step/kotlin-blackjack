package blackjack.domain.card.state

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

interface State {
    val cards: PlayingCards

    fun draw(playingCard: PlayingCard): State

    fun stay(): State

    fun isFinished(): Boolean
}
