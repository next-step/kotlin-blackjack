package blackjack.domain

class Cards(
    _cards: List<Card> = emptyList()
) {

    val cards = _cards.toMutableList()
    fun get(index: Int): Card {
        return cards[index]
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun score(): Int {
        val numberOfAce = numberOfAce()
        val baseScore = baseScore()
        return if (numberOfAce != 0 && baseScore + ACE_POINT <= BLACKJACK) baseScore + ACE_POINT else baseScore
    }

    private fun numberOfAce(): Int {
        return cards.count {
            it.denomination == Denomination.ACE
        }
    }

    private fun baseScore() = cards.sumOf { it.denomination.score }

    companion object {
        private const val BLACKJACK = 21
        private const val ACE_POINT = 10
        fun of(cards: List<Card>): Cards {
            return cards.shuffled().toCards()
        }
    }
}

private fun List<Card>.toCards() = Cards(this)
