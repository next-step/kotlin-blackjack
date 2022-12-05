package blackjack.domain

class Dealer(deck: Cards = Deck().cards, val cards: Cards = Cards()) {
    private var _list: MutableList<Card> = deck.list.toMutableList()

    val deck: Cards
        get() = Cards(_list.toList())

    fun divede(): Card = _list.removeFirst()

    fun shuffle() {
        _list = _list.shuffled().toMutableList()
    }
}
