package blackjack.domain.game

@JvmInline
value class Profit(val value: Int) {

    constructor(value: Double) : this(value.toInt())

    fun negative() = Profit(-1 * value)

    operator fun plus(other: Profit): Profit {
        return Profit(value + other.value)
    }

    companion object {
        private const val BLACK_JACK_PROFIT = 0.5

        val ZERO = Profit(0)

        fun win(money: Money) = Profit(money)

        fun draw() = ZERO

        fun lose(money: Money) = Profit(money).negative()

        fun blackJack(money: Money) = Profit(BLACK_JACK_PROFIT * money.value)

        fun List<Profit>.sum(): Profit {
            return fold(ZERO) { acc, money -> acc + money }
        }
    }
}

fun Profit(money: Money): Profit {
    return Profit(money.value)
}
