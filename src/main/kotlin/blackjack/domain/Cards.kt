package blackjack.domain

data class Cards(
    val cards: MutableList<Card> = mutableListOf(),
) {
    fun add(card: Card) {
        cards.add(card)
    }
}
