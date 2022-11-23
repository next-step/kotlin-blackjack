package blackjack

class PlayingCards(
    private val cards: MutableSet<Card>
) {
    fun sumPoint(): Int {
        return cards.sumOf { it.value }
    }
}
