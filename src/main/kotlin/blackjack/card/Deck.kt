package blackjack.card

class Deck(
    private var _cards: MutableList<Card>
) {
    private val cards: List<Card> get() = _cards

    fun shuffle() {
        _cards.shuffle()
    }
}
