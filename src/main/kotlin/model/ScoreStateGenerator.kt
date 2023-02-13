package model

class ScoreStateGenerator {
    fun generate(score: Int): ScoreState {
        return when {
            score > BLACK_JACK -> ScoreState.BUST
            score == BLACK_JACK -> ScoreState.BLACKJACK
            else -> ScoreState.HIT
        }
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
