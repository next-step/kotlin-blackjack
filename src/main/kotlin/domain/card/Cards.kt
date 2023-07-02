package domain.card

class Cards {
    private val cards = mutableSetOf<Card>()

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

        if (sumOfAllCases.contains(MAX_NUMBER)) {
            return MAX_NUMBER
        }

        return sumOfAllCases.min()
    }

    fun canReceiveMoreCard(): Boolean {
        return result().lessThanMaxNumber()
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
        return cards.filter {
            it.denomination == Denomination.ACE
        }.size
    }

    private fun sumOfAceCases(): List<Int> {
        val numberOfAce = numberOfAce()
        return List(numberOfAce + 1) { index ->
            with(Denomination.ACE.numbers) {
                first() * index + last() * (numberOfAce - index)
            }
        }
    }

    private fun Int.lessThanMaxNumber(): Boolean {
        return this < MAX_NUMBER
    }

    companion object {
        private const val MAX_NUMBER = 21
    }
}
