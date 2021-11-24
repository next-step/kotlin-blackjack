package blackjack.domain.game

@JvmInline
value class Profit(val value: Int) {

    constructor(value: Double) : this(value.toInt())

    fun negative() = Profit(-1 * value)

    operator fun plus(other: Profit): Profit {
        return Profit(value + other.value)
    }

    companion object {
        val ZERO = Profit(0)

        fun List<Profit>.sum(): Profit {
            return fold(ZERO) { acc, money -> acc + money }
        }
    }
}
