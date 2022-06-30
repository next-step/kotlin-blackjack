package blackjack.domain

class Hands(
    cards: Set<Card>
) {
    constructor() : this(setOf())

    private val _cards = cards.toMutableSet()

    val cards: Set<Card>
        get() = _cards.toSet()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun hasAce(): Boolean = _cards.any { it.isAce() }

    fun sumOfPoints(): Int = _cards.sumOf { it.numberValue }

    fun sumOfPointsWithAce(): Int {
        var result = sumOfPointsHasNoAce()
        _cards.filter { it.isAce() }
            .forEach {
                result += it.getAceNumberValue(result)
            }
        return result
    }

    private fun sumOfPointsHasNoAce(): Int =
        _cards.filterNot { it.isAce() }
            .sumOf { it.numberValue }
}
