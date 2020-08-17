package blackjack.domain

const val REPLY_YES = "y"
const val REPLY_NO = "n"

enum class PlayerState(private val wantToDraw: String, private val score: Int = 0) {
    HIT(REPLY_YES),
    STAND(REPLY_NO),
    BLACKJACK(REPLY_NO, 21),
    BUST(REPLY_NO, 22);

    companion object {

        fun valueOfState(reply: String, score: Int, cardCount: Int = 2): PlayerState {
            if (score == BLACKJACK.score && cardCount == 2) return BLACKJACK

            if (score >= BUST.score) return BUST

            return values().firstOrNull { HIT.wantToDraw == reply } ?: STAND
        }
    }
}
