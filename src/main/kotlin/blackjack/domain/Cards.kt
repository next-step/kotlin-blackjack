package blackjack.domain

class Cards(
    _cards: List<Card> = emptyList()
) {

    val cards = _cards.toMutableList()
    fun get(index: Int): Card {
        require(index <= 52) { "카드의 인덱스는 52가 넘을 수 없습니다." }
        return cards[index]
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun score(): Int {
        val baseScore = baseScore()
        return if (containsAce() && baseScore + ACE_POINT <= BLACKJACK) baseScore + ACE_POINT else baseScore
    }

    private fun containsAce(): Boolean {
        return cards.any {
            it.denomination == Denomination.ACE
        }
    }

    private fun baseScore() = cards.sumOf { it.denomination.score }

    companion object {
        const val BLACKJACK = 21
        private const val ACE_POINT = 10
        fun shuffled(cards: List<Card>): Cards {
            return cards.shuffled().toCards()
        }
    }
}

private fun List<Card>.toCards() = Cards(this)
