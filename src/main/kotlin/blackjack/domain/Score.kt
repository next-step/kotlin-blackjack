package blackjack.domain

import blackjack.domain.card.Ace
import blackjack.domain.card.Cards

/**
 * Created by Jaesungchi on 2022.06.14..
 */
@JvmInline
value class Score(val value: Int) {

    fun isBust(): Boolean {
        return value > BLACKJACK_SCORE
    }

    fun isBlackJackScore(): Boolean {
        return value == BLACKJACK_SCORE
    }

    operator fun compareTo(score: Score): Int {
        return compareValues(this.value, score.value)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_SUB_SCORE = 10

        fun of(cards: Cards): Score {
            var score = cards.hands.sumOf { it.score }
            if (cards.hands.any { it is Ace } && score + ACE_SUB_SCORE <= BLACKJACK_SCORE)
                score += ACE_SUB_SCORE
            return Score(score)
        }
    }
}
