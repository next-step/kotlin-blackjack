package blackjack.domain

class Hand {
    private val _cards = mutableSetOf<Card>()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun score(): Int {
        var score = cards.sumOf { it.score() }
        val aceCount = cards.count { it.isAce() }

        repeat(aceCount) {
            if (score + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE) {
                score += ACE_ADDITIONAL_SCORE
            }
        }

        return score
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun count(): Int = cards.size

    companion object {
        const val ACE_ADDITIONAL_SCORE = 10
    }
}
