package next.step.blackjack.util

import next.step.blackjack.domain.card.Card

object CombinationUtils {
    fun possiblePoints(cards: List<Card>): Set<Int> = cards.map { setOf(it.minPoint(), it.maxPoint()) }
        .reduceOrNull { points, minAndMax -> points.flatMap { point -> minAndMax.map { it + point } }.toSet() }
        ?: emptySet()
}
