package blackjack.domain

class Cards(list: List<Card> = listOf()) {
    private val _list: MutableList<Card> = list.toMutableList()

    val list: List<Card>
        get() = _list.toList()

    fun add(card: Card) {
        _list.add(card)
    }

    fun count(): Int = _list.size
}
