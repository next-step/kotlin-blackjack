package blackjack.domain

class Cards private constructor(
    val cards: List<Card>
) {
    override fun toString(): String = cards.joinToString(", ") { it.toString() }

    fun add(newCard: Card): Cards {
        val newCards = cards.toMutableList()
        newCards.add(newCard)
        return Cards(newCards)
    }

    fun maxSumOfTwoCards(): Int {
        if (cards.size < 2) {
            throw IllegalArgumentException("최소 2개의 카드가 있어야 합니다.")
        }

        return cards.flatMapIndexed { index, card1 ->
            cards.subList(index + 1, cards.size)
                .map { card2 -> card1.numbers.sum() + card2.numbers.sum() }
        }.maxOrNull() ?: 0
    }

    companion object {
        fun empty(): Cards = Cards(emptyList())
    }
}
