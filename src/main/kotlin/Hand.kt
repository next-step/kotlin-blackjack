import card.Denomination
import card.PlayingCard

class Hand(cards: List<PlayingCard>) {
    private val _cards: MutableList<PlayingCard> = cards.toMutableList()
    val cards: List<PlayingCard>
        get() = _cards.toList()

    val size: Int
        get() = _cards.size

    fun add(card: PlayingCard) {
        _cards.add(card)
    }

    fun score(): Int {
        val total = _cards.sumOf { it.denomination.score }
        if (_cards.any { it.denomination == Denomination.ACE } && total <= 11) return total + BONUS
        return total
    }

    fun isBust(): Boolean {
        return score() > MAX_SCORE
    }

    fun isBlackjack(): Boolean {
        return size == BLACKJACK_SIZE && score() == MAX_SCORE
    }

    companion object {
        private const val BONUS = 10
        private const val MAX_SCORE = 21
        private const val BLACKJACK_SIZE = 2
    }
}
