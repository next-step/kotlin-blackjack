package blackjack.domain.participant.state

import blackjack.domain.participant.GameResult
import blackjack.domain.participant.GameResult.DRAW
import blackjack.domain.participant.GameResult.LOSE
import blackjack.domain.participant.GameResult.WIN

data class Score(
    val value: Int,
) {
    val isBust: Boolean = this.value > BLACKJACK

    val isBlackjack: Boolean = this.value == BLACKJACK

    fun compareGameResult(other: Score): GameResult = when {
        this.value > other.value -> WIN
        this.value < other.value -> LOSE
        else -> DRAW
    }

    fun increaseAceScoreBeforeBust(countOfAce: Int): Score {
        var increaseValue = this.value

        repeat(countOfAce) {
            if (increaseValue + INCREASE_ACE_SCORE <= BLACKJACK) {
                increaseValue += INCREASE_ACE_SCORE
            }
        }

        return Score(value = increaseValue)
    }

    operator fun compareTo(score: Score): Int = this.value.compareTo(score.value)

    companion object {
        private const val BLACKJACK = 21
        private const val INCREASE_ACE_SCORE = 10
    }
}
