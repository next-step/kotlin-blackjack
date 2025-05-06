package card

class Deck(cards: List<PlayingCard>) {
    private val _cards = cards.toMutableList()
    val cards: List<PlayingCard>
        get() = _cards.toList()

    fun drawOne(): PlayingCard {
        return _cards.removeFirst()
    }
}
