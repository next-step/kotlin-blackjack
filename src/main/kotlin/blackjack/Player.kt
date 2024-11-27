package blackjack

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }
}
