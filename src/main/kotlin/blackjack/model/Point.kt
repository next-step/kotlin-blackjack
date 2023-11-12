package blackjack.model

import blackjack.dto.Number

class Point(
    private val numbers: List<Number>
) {

    fun calculatePoints(): Int {
        var pointSet = mutableSetOf(0)
        numbers.forEach {
            val newPoint = mutableSetOf<Int>()
            pointSet.addPoint(it.point, newPoint)
            it.otherPoint?.let { point ->
                pointSet.addPoint(point, newPoint)
            }
            pointSet = newPoint
        }

        return pointSet.filter { it <= WINNING_POINT }
            .maxOrNull() ?: pointSet.min()
    }

    private fun MutableSet<Int>.addPoint(point: Int, newSet: MutableSet<Int>) {
        forEach {
            newSet.add(it + point)
        }
    }

    companion object {
        const val WINNING_POINT = 21
    }
}
