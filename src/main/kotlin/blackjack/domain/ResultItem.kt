package blackjack.domain

data class ResultItem(
    val win: Int = 0,
    val draw: Int = 0,
    val lose: Int = 0
) {
    fun getEnemyResultItem(): ResultItem {
        return ResultItem(
            win = lose,
            draw = draw,
            lose = win,
        )
    }
}
