package blackjack.domain

class Hands(
    cards: Set<Card>
) {
    constructor() : this(setOf())

    private val _cards = cards.toMutableSet()

    val cards: Set<Card>
        get() = _cards.toSet()

    val hasAce: Boolean
        get() = _cards.any { it.isAce }

    val sumOfPoints: Int
        get() = _cards.sumOf { it.numberValue }

    val sumOfPointsWithAce: Int
        get() {
            var result = sumOfPointsHasNoAce
            _cards.filter { it.isAce }
                .forEach {
                    result += it.getAceNumberValue(result)
                }
            return result
        }

    private val sumOfPointsHasNoAce: Int
        get() = _cards.filterNot { it.isAce }
            .sumOf { it.numberValue }

    fun add(card: Card) {
        _cards.add(card)
    }
}
