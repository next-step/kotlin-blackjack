package blackjack.domain.card.state

import blackjack.domain.bet.Money
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

sealed class State {
    abstract val cards: PlayingCards

    abstract val rate: Double

    abstract val isFinished: Boolean

    abstract fun draw(playingCard: PlayingCard): State

    abstract fun stay(): State

    abstract fun earningRate(money: Money): Double
}
