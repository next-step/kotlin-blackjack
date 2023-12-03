package blackjack.domain

class Hand(cards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    fun getCount(): Int {
        return _cards.count()
    }

    fun getScore(): Int {
        val possibleScore = getPossibleScore()

        if (possibleScore.count() == 1) {
            return possibleScore.first()
        }

        return possibleScore.filter {
            it <= BlackjackRule.TARGET_SCORE
        }.let {
            if (it.isNotEmpty()) {
                return it.max()
            }

            possibleScore.min()
        }
    }

    private fun getPossibleScore(): List<Int> {
        val standardScore = _cards.sumOf { it.number.score }

        return (0..countAce()).map {
            standardScore + (it * 10)
        }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    private fun countAce(): Int =
        _cards.count {
            it.number == CardNumber.ACE
        }
}
