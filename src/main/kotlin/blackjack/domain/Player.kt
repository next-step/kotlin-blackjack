package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand.empty()
) {
    fun initialize(initialCards: Pair<Card, Card>) {
        with(initialCards) {
            hand.add(first)
            hand.add(second)
        }
    }

    fun hit(card: Card) {
        hand.add(card)
    }

    fun calculateHand(): Int = hand.calculate()
}
