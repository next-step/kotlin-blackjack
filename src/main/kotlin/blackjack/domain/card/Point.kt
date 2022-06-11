package blackjack.domain.card

@JvmInline
value class Point(
    val value: Int
) {
    operator fun plus(other: Point): Point = Point(value + other.value)
    operator fun compareTo(other: Point): Int = value compareTo other.value
    operator fun times(times: Int): Point = Point(value * times)

    companion object {
        val BLACKJACK = Point(21)
    }
}
