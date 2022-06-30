package blackjack.domain

class Player(val name: String) {
    val hands = Hands()

    fun receiveCard(card: Card) {
        hands.add(card)
    }

    fun canHit() = sumOfPoints() < MAX_POINT

    fun sumOfPoints(): Int =
        if (hands.hasAce()) {
            hands.sumOfPointsWithAce()
        } else {
            hands.sumOfPoints()
        }

    companion object {
        private const val MAX_POINT = 21
    }
}
