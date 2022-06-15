package blackjack.domain

import blackjack.domain.card.ACE
import blackjack.domain.card.Cards

/**
 * Created by Jaesungchi on 2022.06.14..
 */
@JvmInline
value class Score(val value: Int) {

    companion object {
        private const val ACE_SUB_SCORE = 10

        fun of(cards: Cards): Score {
            var score = cards.hands.sumOf { it.score }
            if (cards.hands.any { it is ACE } && score + ACE_SUB_SCORE <= Cards.BLACKJACK_SCORE)
                score += ACE_SUB_SCORE
            return Score(score)
        }
    }
}
