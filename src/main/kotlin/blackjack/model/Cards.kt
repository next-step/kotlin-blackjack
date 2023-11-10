package blackjack.model

data class Cards(
    private val cards: MutableSet<Card> = mutableSetOf()
)
