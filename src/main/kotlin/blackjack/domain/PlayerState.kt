package blackjack.domain

import blackjack.domain.Game.Companion.DEFAULT_CARD_COUNT

const val YES = "y"
const val NO = "n"

enum class PlayerState(private val needDrawing: String, private val score: Int = 0) {
    HIT(YES),
    STAND(NO),
    BLACKJACK(NO, 21),
    BUST(NO, 22);

    companion object {

        fun valueOfState(reply: String, score: Int, cardCount: Int = DEFAULT_CARD_COUNT): PlayerState {
            if (score == BLACKJACK.score && cardCount == DEFAULT_CARD_COUNT) return BLACKJACK

            if (score >= BUST.score) return BUST

            return values().firstOrNull { HIT.needDrawing == reply } ?: STAND
        }
    }
}
