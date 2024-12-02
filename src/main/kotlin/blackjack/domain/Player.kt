package blackjack.domain

data class Player(
    val name: String,
    val ownedCards: MutableList<Card> = mutableListOf(),
) {
    fun hit(card: Card) {
        ownedCards.add(card)
    }
}
