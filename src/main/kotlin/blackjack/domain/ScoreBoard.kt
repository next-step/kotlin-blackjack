package blackjack.domain

class ScoreBoard {

    private var win: Int = 0
    private var lose: Int = 0
    private var draw: Int = 0

    fun winTo(opponent: ScoreBoard) {
        this.win++
        opponent.lose++
    }

    fun loseTo(opponent: ScoreBoard) {
        this.lose++
        opponent.win++
    }

    fun draw(opponent: ScoreBoard) {
        this.draw++
        opponent.draw++
    }

    fun resultForPlayer(): String {
        return when {
            win > 0 -> winComment
            lose > 0 -> loseComment
            else -> drawComment
        }
    }

    fun resultForDealer(): String {
        return "${win}승 ${lose}패 ${draw}무"
    }

    companion object {
        private const val winComment = "승"
        private const val loseComment = "패"
        private const val drawComment = "무"
    }
}
