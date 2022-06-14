package blackjack.domain.participant.vo

@JvmInline
value class Amount(
    val value: Int
) : Comparable<Amount> {

    operator fun plus(other: Amount): Amount = Amount(value + other.value)

    operator fun minus(other: Amount): Amount = Amount(value - other.value)

    operator fun unaryMinus(): Amount = Amount(-value)

    override fun compareTo(other: Amount): Int = value - other.value
}
