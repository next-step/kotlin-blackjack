package blackjack.domain.game.result

import blackjack.domain.participant.Participant

open class MatchResult(
    val participant: Participant,
    val winScore: Int = DEFAULT_SCORE,
    val loseScore: Int = DEFAULT_SCORE,
) {

    companion object {
        const val FLAG_SCORE: Int = 1
        private const val DEFAULT_SCORE: Int = 0
    }
}
