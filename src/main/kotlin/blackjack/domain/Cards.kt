package blackjack.domain

@JvmInline
value class Cards(val value: List<Card>) {
    fun getPoints(cardPointStrategy: CardPointStrategy): Int {
        return value.sumOf { card ->
            cardPointStrategy.getPoint(card.rank)
        }
    }
}
