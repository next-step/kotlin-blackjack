package blackjack

@JvmInline
value class Scores(private val values: List<Score>) {
    fun totalScore(): Score {
        return this.values.reduce { total: Score, score: Score -> total + score }
    }
}
