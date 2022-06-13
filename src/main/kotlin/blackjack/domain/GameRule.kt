package blackjack.domain

@JvmInline
value class Blackjack(val score: Int = 21)

@JvmInline
value class InitDrawCard(val count: Int = 2)

@JvmInline
value class TotalCard(val count: Int = 52)
