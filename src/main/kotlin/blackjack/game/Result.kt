package blackjack.game

data class Result(
    private var win: Int = 0,
    private var draw: Int = 0,
    private var lose: Int = 0,
) {
    fun addWin() = win++
    fun addDraw() = draw++
    fun addLose() = lose++

    fun printResult() = "$win 승 $draw 무 $lose 패"
}
