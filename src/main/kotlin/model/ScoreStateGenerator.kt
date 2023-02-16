package model

class ScoreStateGenerator {
    fun generate(cards: List<Card>): ScoreState {
        val score = CardNumberCalculator.sumCardNumbers(cards)
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
