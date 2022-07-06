package camp.nextstep.blackjack.card

class DrawnCard(val card: Card) {

    private var turnedUp: Boolean = false

    val isFaceDown get() = !turnedUp
    val isFaceUp get() = turnedUp

    fun turnUp() {
        turnedUp = true
    }
}
