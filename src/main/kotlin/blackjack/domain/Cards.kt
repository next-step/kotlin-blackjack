package blackjack.domain

class Cards(list: List<Card> = listOf()) {
    private val _list: MutableList<Card> = list.toMutableList()

    val list: List<Card>
        get() = _list.toList().sortedBy { it.number.ordinal }

    override fun toString(): String = list.joinToString()

    fun add(card: Card) {
        _list.add(card)
    }

    fun count(): Int = list.size
}
