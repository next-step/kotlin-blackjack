package blackjack.domain.player.state

import blackjack.domain.card.Score
import blackjack.domain.player.state.hands.Hands
import blackjack.error.InvalidCalculateScoreException
import blackjack.error.InvalidMatchException

sealed class Running(hands: Hands) : PlayerState(hands) {

    override fun isFinished(): Boolean = false

    override fun score(): Score = throw InvalidCalculateScoreException(this::class.toString())

    override fun match(other: PlayerState): MatchResult = throw InvalidMatchException(this::class.toString())

    override fun stay(): PlayerState = Stay(hands)
}
