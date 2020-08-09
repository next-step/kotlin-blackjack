package blackJack.domain

object TotalScore {
    fun getScore(cards: List<Int>): Int {
        return cards.sum()
    }
}
