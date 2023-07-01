package domain

import domain.card.Card
import domain.card.Denomination

class Player(val name: String) {
    private val cards = mutableSetOf<Card>()

    fun dealCard(card: Card) {
        this.cards.add(card)
    }

    fun cards(): Set<Card> {
        return cards.toSet()
    }

    fun canReceiveMoreCard(): Boolean {
        if (doesNotHaveAce()) {
            return sumWithoutAce().lessThanMaxNumber()
        }

        val sumOfAllCases = sumOfAceCases().map {
            sumWithoutAce() + it
        }

        if (sumOfAllCases.contains(MAX_NUMBER)) {
            return false
        }

        return sumOfAllCases.any { it < MAX_NUMBER }
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

    private fun doesNotHaveAce(): Boolean {
        return numberOfAce() == 0
    }

    private fun Int.lessThanMaxNumber(): Boolean {
        return this < MAX_NUMBER
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
        private const val MAX_NUMBER = 21
    }
}
