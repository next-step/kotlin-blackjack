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
        while (sum <= 11 && aceCount > 0) {
            sum += 10
            aceCount.minus(1)
        }
        return sum
    }
}
