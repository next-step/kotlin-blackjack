package blackjack.domain.model

@JvmInline
value class Score(val score: Int) {
    operator fun plus(score: Score): Score {
        return Score(this.score + score.score)
    }

    operator fun compareTo(score: Int): Int {
        return this.score - score
    }
}
