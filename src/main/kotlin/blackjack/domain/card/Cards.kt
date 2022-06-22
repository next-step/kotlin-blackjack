package blackjack.domain.card

@JvmInline
value class Cards(
    private val value: MutableList<Card> = mutableListOf(),
)
