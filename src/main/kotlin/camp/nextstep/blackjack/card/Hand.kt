package camp.nextstep.blackjack.card

class Hand {
    private val _cards = mutableListOf<DrawnCard>()
    val cards get() = _cards.map { it.card }.toList()
    val faceUpCards get() = _cards.filter { it.isFaceUp }.map { it.card }.toList()
    val faceDownCardCount get() = _cards.filter { it.isFaceDown }.size

    fun isEmpty() = _cards.isEmpty()

    fun firstCardIsAce() = _cards[0].card.number == CardNumber.ACE

    fun firstCardIsTen() = _cards[0].card.number == CardNumber.TEN

    fun add(card: DrawnCard) {
        this._cards.add(card)
    }

    fun turnUp() {
        _cards.forEach {
            it.turnUp()
        }
    }
}
