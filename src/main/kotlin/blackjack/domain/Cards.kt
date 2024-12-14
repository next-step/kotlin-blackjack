package blackjack.domain

class Cards {
    private val _group: MutableList<Card> = mutableListOf()
    val group: List<Card> get() = _group.toList()

    fun size(): Int = group.size

    fun add(card: Card): Boolean {
        return _group.add(card)
    }
}
