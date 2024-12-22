package blackjack.domain.state

import blackjack.domain.card.PlayingCard

interface State {
    val hands: Hands

    fun draw(card: PlayingCard): State

    fun stay(): State

    fun profit(money: Int): Double
    fun isRunning(): Boolean
}
