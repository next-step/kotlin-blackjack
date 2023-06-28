package blackjack.domain

class Score(
    val value: Int
) {

    fun isScoreBelowStandard(): Boolean {
        if(STANDARD_CARD_SCORE >= this.value) {
            return true
        }
        return false
    }

    fun isScoreAboveStandard(): Boolean {
        if(STANDARD_CARD_SCORE < this.value) {
            return true
        }
        return false
    }

    fun isScoreAboveBlackjack(): Boolean {
        if(this.value > BLACK_JACK_SCORE) {
            return true
        }
        return false
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val STANDARD_CARD_SCORE = 16
    }
}