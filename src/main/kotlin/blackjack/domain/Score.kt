package blackjack.domain

@JvmInline
value class Score(val score: Int) {

    fun burst(): Boolean {
        return score > BLACK_JACK_SCORE
    }

    operator fun compareTo(score: Score): Int {
        return this.score.compareTo(score.score)
    }

    fun winLose(otherScore: Score): WinLose {
        when {
            this.burst() && otherScore.burst() -> return WinLose.DRAW
            this.burst().not() && otherScore.burst() -> return WinLose.WIN
            this.burst() && otherScore.burst().not() -> return WinLose.LOSE
            this > otherScore -> return WinLose.WIN
            this < otherScore -> return WinLose.LOSE
        }
        return WinLose.DRAW
    }

    companion object {
        const val BLACK_JACK_SCORE = 21
        const val ACE_BONUS = 10
    }
}
