package card

class Deck(cards: List<PlayingCard>) {
    private val _cards = cards.toMutableList()
    val cards: List<PlayingCard>
        get() = _cards.toList()

    fun drawCard(count: Int): List<PlayingCard> {
        return List(count) { _cards.removeFirst() }
    }
}
