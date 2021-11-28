package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidDrawException
import blackjack.error.InvalidMapToPlayStateException

sealed class Finish(hands: Hands) : State(hands) {

    abstract fun earningsRate(state: State): Double

    override fun isFinished(): Boolean = true

    override fun score(): Score = hands.score()

    override fun draw(card: Card): State = throw InvalidDrawException(this::class.toString())

    override fun profit(otherState: State, money: Money): Double = money.money * earningsRate(otherState)

    override fun stay(): State = throw InvalidMapToPlayStateException(this::class.toString())
}
