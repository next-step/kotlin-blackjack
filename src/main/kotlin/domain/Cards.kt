package domain

data class Cards(private val cards: MutableList<Card>) {
    fun getTotalScore(): Int {
        return cards.map { it.score() }.sum()
    }
}
