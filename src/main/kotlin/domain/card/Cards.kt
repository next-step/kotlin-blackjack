package domain.card

class Cards(cards: List<Card> = emptyList()) {

    private val cards: MutableList<Card> = cards.toMutableList()

    fun add(card: Card) {
        cards.add(card)
    }

    fun current(): Set<Card> {
        return cards.toSet()
    }

    fun result(): Int {
        val sumWithoutAce = sumWithoutAce()

        if (doesNotHaveAce()) {
            return sumWithoutAce
        }

        val sumOfAllCases = sumOfAceCases().map {
            sumWithoutAce + it
        }

        if (BLACKJACK_POINT in sumOfAllCases) {
            return BLACKJACK_POINT
        }

        return sumOfAllCases.min()
    }

    private fun doesNotHaveAce(): Boolean {
        return numberOfAce() == 0
    }

    private fun sumWithoutAce(): Int {
        return cards.filterNot {
            it.denomination == Denomination.ACE
        }.sumOf {
            it.numbers.first()
        }
    }

    private fun numberOfAce(): Int {
        return cards.count {
            it.denomination == Denomination.ACE
        }
    }

    private fun sumOfAceCases(): List<Int> {
        val numberOfAce = numberOfAce()
        return List(numberOfAce + 1) { index ->
            with(Denomination.ACE.numbers) {
                first() * index + last() * (numberOfAce - index)
            }
        }
    }

    companion object {
        const val BLACKJACK_POINT = 21

        fun all(): Cards {
            return Cards(Card.all())
        }
    }
}
