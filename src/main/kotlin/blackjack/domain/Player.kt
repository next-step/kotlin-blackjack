package blackjack.domain

data class Player(
    val name: String,
    val ownedCards: MutableList<Card> = mutableListOf(),
) {
    fun receiveCard(card: Card) {
        ownedCards.add(card)
    }

    fun stay() {
    }
}
