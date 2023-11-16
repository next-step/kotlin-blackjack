package blackjack.business

class PlayerCards(cards: List<Card> = listOf()) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    operator fun get(i: Int): Card {
        return _cards[i]
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        var sum = _cards.sumOf { it.rank.score }
        var aceCount = _cards.count { it.rank == Rank.ACE }
        while (sum + ACE_OFFSET <= BLACKJACK && aceCount > 0) {
            sum += ACE_OFFSET
            aceCount--
        }
        return sum
    }

    fun canDrawCard(): Boolean {
        return sum() < BLACKJACK
    }

    companion object {
        private const val BLACKJACK = 21
        private const val ACE_OFFSET = 10
    }
}
