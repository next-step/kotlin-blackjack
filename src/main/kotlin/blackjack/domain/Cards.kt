package blackjack.domain

data class Cards(val cards: List<Card>) {
    fun calculateScore(): Int {
        return cards.sumOf { it.rank.score }
    }
}
