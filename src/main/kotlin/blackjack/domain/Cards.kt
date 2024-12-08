package blackjack.domain

class Cards(
    cards: List<Card>,
) {
    private val value: MutableList<Card> = cards.toMutableList()

    val currentCards: List<DrawCard>
        get() = value
            .map { it.toDrawCard() }
            .toList()

    fun add(card: Card) {
        value.add(card)
    }

    fun calculateTotalScore(): Int =
        value
            .sortedBy { sortForAceAfter(it) }
            .fold(0) { acc, card -> acc + card.value(acc) }

    private fun sortForAceAfter(card: Card): Int =
        if (card.isAce()) {
            1
        } else {
            0
        }
}
