package blackjack.domain

open class Player(val name: String) {
    val hands = Hands()

    open val canNotHit: Boolean
        get() = sumOfPoints >= BLACKJACK_POINT

    val sumOfPoints: Int
        get() = if (hands.hasAce) {
            hands.sumOfPointsWithAce
        } else {
            hands.sumOfPoints
        }

    val status: Status
        get() = if (sumOfPoints == BLACKJACK_POINT && hands.cards.size == 2) {
            Status.BLACKJACK
        } else if ((1..21).contains(hands.sumOfPoints)) {
            Status.HIT
        } else {
            Status.BUST
        }

    fun receiveCard(card: Card) {
        hands.add(card)
    }

    fun compareScore(otherPlayer: Player): Int =
        sumOfPoints.compareTo(otherPlayer.sumOfPoints)

    companion object {
        private const val BLACKJACK_POINT = 21
    }
}
