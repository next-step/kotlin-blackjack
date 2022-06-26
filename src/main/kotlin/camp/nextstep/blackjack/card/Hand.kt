package camp.nextstep.blackjack.card

class Hand {
    private val _cards = mutableListOf<DrawnCard>()
    val cards get() = _cards.toList()

    fun isEmpty() = _cards.isEmpty()

    fun add(card: DrawnCard) {
        this._cards.add(card)
    }
}
