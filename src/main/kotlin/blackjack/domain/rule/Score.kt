package blackjack.domain.rule

class Score(
    val winNum: Int = 0,
    val drawNum: Int = 0,
    val loseNum: Int = 0
) {

    fun reverse(): Score {
        return Score(loseNum, drawNum, winNum)
    }

    fun getTotalGame(): Int {
        return winNum + drawNum + loseNum
    }

    fun win(cnt: Int = 1): Score {
        return Score(winNum + cnt, drawNum, loseNum)
    }

    fun draw(cnt: Int = 1): Score {
        return Score(winNum, drawNum + cnt, loseNum)
    }

    fun lose(cnt: Int = 1): Score {
        return Score(winNum, drawNum, loseNum + cnt)
    }

    operator fun plus(other: Score): Score {
        return Score(this.winNum + other.winNum, this.drawNum + other.drawNum, this.loseNum + other.loseNum)
    }

    override operator fun equals(other: Any?): Boolean {
        return this.winNum == (other as Score).winNum &&
            this.drawNum == (other as Score).drawNum &&
            this.loseNum == (other as Score).loseNum
    }
}
