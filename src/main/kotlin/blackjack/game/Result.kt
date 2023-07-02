package blackjack.game

data class Result(
    private var win: Int = 0,
    private var draw: Int = 0,
    private var lose: Int = 0,
) {
    fun addWin() = win++
    fun addDraw() = draw++
    fun addLose() = lose++

    fun printDealerResult() = "${win}승 ${draw}무 ${lose}패"
    fun printPlayerResult() = when {
        win > 0 -> "승"
        draw > 0 -> "무"
        else -> "패"
    }
}
