package blackjack.entity

class Player(
    val name: String,
) {
    val hand: Hand = Hand()

    fun receiveCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int {
        return hand.calculateScore()
    }
}
