package blackjack.domain

import blackjack.domain.Game.Companion.DEFAULT_CARD_COUNT

const val YES = "y"
const val NO = "n"

enum class State(private val goDrawing: String = NO, private val score: Int = 0) {
    HIT(YES),
    STAY(NO),
    BLACKJACK(NO, 21),
    BUST(NO, 22);

    companion object {

        fun valueOfState(reply: String = NO, score: Int = 0, cardCount: Int = DEFAULT_CARD_COUNT): State {
            if (score == BLACKJACK.score && cardCount == DEFAULT_CARD_COUNT) return BLACKJACK

            if (score >= BUST.score) return BUST

            return values().firstOrNull { reply == HIT.goDrawing } ?: STAY
        }
    }
}
