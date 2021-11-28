package blackjack.domain.player.state

import blackjack.domain.bet.Money
import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidCalculateScoreException
import blackjack.error.InvalidProfitException

sealed class Running(hands: Hands) : State(hands) {

    override fun isFinished(): Boolean = false

    override fun score(): Score = throw InvalidCalculateScoreException(this::class.toString())

    override fun profit(otherState: State, money: Money): Double = throw InvalidProfitException(this::class.toString())

    override fun stay(): State = Stay(hands)
}
