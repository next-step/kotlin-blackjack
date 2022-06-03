package blackjack.domain

class Player(
    val hand: Hand = Hand.EMPTY
) {
    fun initialize(pair: Pair<Card, Card>) {
        with(pair) {
            hand.add(first)
            hand.add(second)
        }
    }

    fun hit(card: Card) {
        hand.add(card)
    }

    fun calculate(): Int = hand.point
}
