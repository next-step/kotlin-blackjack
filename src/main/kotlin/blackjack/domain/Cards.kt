package blackjack.domain

@JvmInline
value class Cards(val value: List<Card>) {
    fun getPoints(): Int {
        val hardAcePoint = value.sumOf { card ->
            HardAcePointStrategy().getPoint(card.rank)
        }

        val softAcePointStrategy = value.sumOf { card ->
            SoftAcePointStrategy().getPoint(card.rank)
        }

        return softAcePointStrategy.takeIf { it <= 21 } ?: hardAcePoint
    }

    operator fun plus(other: Card): Cards {
        return Cards(value + other)
    }
}
