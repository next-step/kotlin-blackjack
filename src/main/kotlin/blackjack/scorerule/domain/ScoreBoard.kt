package blackjack.scorerule.domain

class ScoreBoard {

    private var win: Int = 0
    private var lose: Int = 0
    private var draw: Int = 0

    fun win(): Int {
        return win
    }

    fun lose(): Int {
        return lose
    }

    fun draw(): Int {
        return draw
    }

    fun countWin() {
        this.win++
    }

    fun countLose() {
        this.lose++
    }

    fun countDraw() {
        this.draw++
    }
}
