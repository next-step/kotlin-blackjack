package blackjack.domain

class Player(
    val name: String,
) {
    val cards: MutableList<Card> = mutableListOf()

    fun receiveCard(card: Card) {
        cards.add(card)
    }
}
