package blackjack

private const val SPECIAL_ACE_USABLE_BOUNDARY = 10

class PlayingCards(
    private val cards: MutableSet<Card>
) {
    fun sumPoint(): Int {
        val sumPointExceptAces = cards.filter { !it.isAce() }
            .sumOf { it.value }

        val aces = cards.filter { it.isAce() }
        if (aces.isEmpty()) {
            return sumPointExceptAces
        }

        val acesAsOnePoint = aces.size - 1

        val sumWithoutSpecialAce = sumPointExceptAces + acesAsOnePoint
        if (sumWithoutSpecialAce > SPECIAL_ACE_USABLE_BOUNDARY) {
            return sumWithoutSpecialAce + ACE_POINT
        }
        return sumPointExceptAces + SPECIAL_ACE_POINT
    }
}
