package blackjack.domain.player.betting

@JvmInline
value class Betting(
    val amount: Double
)

@JvmInline
value class Profit(
    val amount: Double = 0.0
)

operator fun Profit.plus(other: Profit): Profit =
    Profit(amount + other.amount)

operator fun Profit.unaryMinus(): Profit =
    Profit(-amount)
