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

    fun hasAnotherValue() = _cards.any { it.number.anotherValue != null }

    fun sumOfPoints(): Int = _cards.sumOf { it.number.value }

    fun sumOfPointsWithAnotherValue(): Int {
        var result = sumOfPointsHasNoAnotherValue()
        _cards.filter { it.number.anotherValue != null }
            .forEach {
                result += it.chooseValue(result, it)
            }
        return result
    }

    private fun sumOfPointsHasNoAnotherValue(): Int =
        _cards.filterNot { it.number.anotherValue != null }
            .sumOf { it.number.value }
}
