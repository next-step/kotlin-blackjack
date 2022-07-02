package blackjack.domain

open class Player(val name: String) {
    val hands = Hands()

    open val canNotHit: Boolean
        get() = sumOfPoints() >= MAX_POINT

    fun receiveCard(card: Card) {
        hands.add(card)
    }

    fun sumOfPoints(): Int =
        if (hands.hasAce) {
            hands.sumOfPointsWithAce()
        } else {
            hands.sumOfPoints()
        }

    companion object {
        private const val MAX_POINT = 21
    }
}
