package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand()
) {

    fun addCard(card: Card) {
        hand.add(card)
    }
}
