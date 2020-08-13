package blackjack.model

data class Point(val value: Int) {

    override fun toString(): String = "$value"

    operator fun plus(item: Point): Point = Point(value + item.value)

    operator fun compareTo(item: Point): Int =
        when {
            value > item.value -> 1
            value == item.value -> 0
            else -> -1
        }

    companion object {
        val MAX_POINT = Point(21)
        val ACE_EXTRA_POINT = Point(10)
        val EXTRA_CARD_AVAILABLE_LIMIT_POINT = Point(16)

        fun calculateAccIfAceFirst(acc: Point): Point =
            if (Kinds.isAce(acc.value)) acc + ACE_EXTRA_POINT else acc

        fun isAvailableExtraPoint(acc: Point, cardPoint: Point) =
            Kinds.isAce(cardPoint.value) && (acc + cardPoint + ACE_EXTRA_POINT) <= MAX_POINT

        fun isReachMaxPoint(point: Point) = point >= MAX_POINT
    }
}
