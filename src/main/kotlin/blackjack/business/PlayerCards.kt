package blackjack.business

class PlayerCards {
    val size: Int
        get() = _cards.size
    private val _cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        _cards.add(card)
    }

    operator fun get(i: Int): Card {
        return _cards[i]
    }

    fun sum(): Int {
        var sum = _cards.sumOf { it.rank.score }
        var aceCount = _cards.count { it.rank == Rank.ACE }
        while (sum > BLACKJACK && aceCount > 0) {
            sum -= ACE_OFFSET
            aceCount--
        }
        return sum
    }

    fun joinToString(delimiter: String): String {
        return _cards.joinToString(delimiter)
    }

    companion object {
        private const val BLACKJACK = 21
        private const val ACE_OFFSET = 10
    }
}
