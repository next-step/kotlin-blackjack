package blackjack.domain.model

@JvmInline
value class Score(val score: Int): Comparable<Score> {
    operator fun plus(score: Score): Score {
        return Score(this.score + score.score)
    }

    operator fun minus(score: Int): Score {
        return Score(this.score - score)
    }

    operator fun compareTo(score: Int): Int {
        return this.score - score
    }

    override fun compareTo(score: Score): Int {
        return this.score - score.score
    }
}
