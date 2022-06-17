package blackjack.card

class Cards(vararg value: Card) {

    private var _cards: MutableList<Card> = value.toMutableList()
    val size = _cards.size

    fun drawCard(): Card {
        return _cards.removeFirst()
    }
}
