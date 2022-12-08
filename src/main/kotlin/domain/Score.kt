package domain

class Score(private val defaultScore: Int, val secondScore: Int?) {

    operator fun plus(score: Int) = this.defaultScore + score

    companion object {
        const val MAX_SCORE = 21
    }
}
