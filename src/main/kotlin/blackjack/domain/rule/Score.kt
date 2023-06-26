package blackjack.domain.rule

class Score(
    val win: Int = 0,
    val draw: Int = 0,
    val lose: Int = 0
) {

    fun reverse(): Score {
        return Score(lose, draw, win)
    }

    fun getTotalGame(): Int {
        return win + draw + lose
    }

    operator fun plus(other: Score): Score {
        return Score(this.win + other.win, this.draw + other.draw, this.lose + other.lose)
    }

    override operator fun equals(other: Any?): Boolean {
        return this.win == (other as Score).win &&
            this.draw == (other as Score).draw &&
            this.lose == (other as Score).lose
    }
}
