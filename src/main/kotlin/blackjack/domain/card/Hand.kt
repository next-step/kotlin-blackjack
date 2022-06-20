package blackjack.domain.card

class Hand {
    private var _value: MutableList<Card> = mutableListOf()
    val value
        get() = _value.toList()

    fun add(card: Card) {
        _value.add(card)
    }
}
