package blackjack.domain.player.state

import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidCalculateScoreException

sealed class Running(hands: Hands) : State(hands) {

    override fun isFinished(): Boolean = false

    override fun score(): Score = throw InvalidCalculateScoreException(this::class.toString())

    override fun stay(): State = Stay(hands)
}
