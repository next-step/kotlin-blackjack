package blackjack.game

data class Result(
    val win: Int = 0,
    val draw: Int = 0,
    val lose: Int = 0,
) {
    fun addWin(): Result = this.copy(win = win + 1)
    fun addDraw(): Result = this.copy(draw = draw + 1)
    fun addLose(): Result = this.copy(lose = lose + 1)

    fun printDealerResult() = "${win}승 ${draw}무 ${lose}패"
    fun printPlayerResult(): String = when {
        win > 0 -> "승"
        draw > 0 -> "무"
        else -> "패"
    }
}
