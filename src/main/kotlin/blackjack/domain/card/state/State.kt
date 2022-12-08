package blackjack.domain.card.state

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

interface State {
    fun draw(playingCard: PlayingCard): State

    fun stay(): State

    fun isFinished(): Boolean

    fun cards(): PlayingCards
}
