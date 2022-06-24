package blackjack.domain

data class Score(val number: Int) {
    val isBust = number > BLACKJACK_SCORE
    val stay = number < BLACKJACK_SCORE

    operator fun plus(score: Score) = Score(number + score.number)

    fun isGreater(score: Score) = number > score.number

    companion object {
        const val BLACKJACK_SCORE = 21

        fun aceScore(score: Score): Score {
            return if ((score + Denomination.ACE.extraScore).isBust) Denomination.ACE.score else Denomination.ACE.extraScore
        }
    }
}
