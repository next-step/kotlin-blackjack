package blackjack

private const val MAX_POINT = 21
private const val SPECIAL_ACE_USABLE_BOUNDARY = 10

class PlayingCards(
    val cards: MutableSet<Card> = mutableSetOf(),
) {
    fun calculatePoint(): Int {
        val sumPoint = sumPoint()
        if (sumPoint > MAX_POINT)
            return 0
        return sumPoint
    }

    private fun sumPoint(): Int {
        if (noAce()) return cards.sumOf { it.value }

        val sumWithoutSpecialAce = cards.sumOf { it.value } - ACE_POINT
        if (sumWithoutSpecialAce > SPECIAL_ACE_USABLE_BOUNDARY) {
            return sumWithoutSpecialAce + ACE_POINT
        }
        return sumWithoutSpecialAce + SPECIAL_ACE_POINT
    }

    private fun noAce() = !cards.any { it.isAce() }

    fun addOne(card: Card) {
        cards.add(card)
    }

    fun addAll(cards: Set<Card>) {
        this.cards.addAll(cards)
    }
}
