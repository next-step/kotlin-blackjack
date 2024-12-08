package blackjack.domain

class Dealer(
    private val playerName: PlayerName,
    cards: List<Card> = listOf(),
) {
    private val cards = cards.toMutableList()

    fun canDraw(): Boolean = totalValue() <= 16

    fun totalValue(): Int =
        cards
            .sortedBy { sortForAceAfter(it) }
            .fold(0) { acc, card -> acc + card.value(acc) }

    private fun sortForAceAfter(card: Card): Int =
        if (card.isAce()) {
            1
        } else {
            0
        }
}
