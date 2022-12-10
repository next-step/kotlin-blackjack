package blackjack

data class Cards(val values: List<Card> = emptyList()) {
    fun add(card: Card) : Cards {
        return Cards(listOf(card) + values)
    }
}