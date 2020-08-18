package blackjack.domain

const val YES = "y"
const val NO = "n"

enum class PlayerState(private val wantToDraw: String, private val score: Int = 0) {
    HIT(YES),
    STAND(NO),
    BLACKJACK(NO, 21),
    BUST(NO, 22);

    companion object {

        fun valueOfState(reply: String, score: Int, cardCount: Int = 2): PlayerState {
            if (score == BLACKJACK.score && cardCount == 2) return BLACKJACK

            if (score >= BUST.score) return BUST

            return values().firstOrNull { HIT.wantToDraw == reply } ?: STAND
        }
    }
}
