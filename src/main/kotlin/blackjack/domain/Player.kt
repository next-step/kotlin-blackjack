package blackjack.domain

data class Player(
    val name: String,
    val hand: Hand = Hand()
) {
    fun canHit(): Boolean {
        return hand.canHit()
    }

    fun hit(deck: Deck): Player {
        return copy(hand = hand.hit(deck))
    }
}
