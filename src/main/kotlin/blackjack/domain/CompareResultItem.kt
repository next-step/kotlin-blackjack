package blackjack.domain

data class CompareResultItem(
    val win: Int = 0,
    val draw: Int = 0,
    val lose: Int = 0
) {
    operator fun plus(item: CompareResultItem): CompareResultItem {
        return CompareResultItem(
            win = win + item.win,
            draw = draw + item.draw,
            lose = lose + item.lose
        )
    }

    fun getEnemyResultItem(): CompareResultItem {
        return CompareResultItem(
            win = lose,
            draw = draw,
            lose = win,
        )
    }

    companion object {
        fun win(): CompareResultItem = CompareResultItem(win = 1)
        fun draw(): CompareResultItem = CompareResultItem(draw = 1)
        fun lose(): CompareResultItem = CompareResultItem(lose = 1)
    }
}
