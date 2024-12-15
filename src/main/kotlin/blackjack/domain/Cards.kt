package blackjack.domain

class Cards {
    private val _group: MutableList<Card> = mutableListOf()
    val group: List<Card> get() = _group.toList()

    fun size(): Int = group.size

    fun add(card: Card): Boolean {
        return _group.add(card)
    }

    fun hasAceCards() = group.any { it.isAce() }

    fun getAceCards() = group.filter { it.isAce() }

    fun getSumOfBasicCards(): Int {
        return group.filter { !it.isAce() }.sumOf { it.number.number[0] }
    }

    companion object {
        const val GAME_LIMIT_NUMBER = 21
    }
}
