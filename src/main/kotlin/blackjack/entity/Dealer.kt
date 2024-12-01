package blackjack.entity

class Dealer : Participant("딜러") {
    companion object {
        const val MIN_SCORE_TO_STAND = 17
        const val MAX_SCORE_TO_DRAW = 16
    }

    fun shouldDrawCard(): Boolean {
        val score = calculateScore()
        return score <= 16
    }
}
