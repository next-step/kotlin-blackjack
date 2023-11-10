package blackjack.model

data class Cards(
    val cards: MutableSet<Card> = mutableSetOf()
) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun totalScore(): Int {
        return cards.sumOf { it.rank.score }
    }

    companion object {
        fun init(): Cards {
            return Cards()
        }
    }
}
