package blackjack

data class Point(val value: Int) {

    override fun toString(): String = "$value"

    operator fun plus(point: Point): Point = Point(value + point.value)

    operator fun compareTo(point: Point) =
        when {
            value > point.value -> 1
            value == point.value -> 0
            else -> -1
        }

    companion object {
        private val MAX_POINT = Point(21)
        private val EXTRA_ACE_POINT = Point(10)

        fun isAvailableExtraPoint(acc: Point, point: Point): Boolean {
            val isAccAce = Denomination.isAce(acc.value)
            val notExceedMaxPoint = acc + point + EXTRA_ACE_POINT <= MAX_POINT
            return isAccAce && notExceedMaxPoint
        }

        fun calculateIfAceFirst(acc: Point): Point =
            if (Denomination.isAce(acc.value)) acc + EXTRA_ACE_POINT else acc
    }
}
