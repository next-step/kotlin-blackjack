package blackjack.domain

open class Player(val name: String) {
    val hands = Hands()

    open val canNotHit: Boolean
        get() = sumOfPoints() >= BLACKJACK_POINT

    fun receiveCard(card: Card) {
        hands.add(card)
    }

    fun sumOfPoints(): Int =
        if (hands.hasAce) {
            hands.sumOfPointsWithAce()
        } else {
            hands.sumOfPoints()
        }

    fun getStatus(): Status {
        if (sumOfPoints() == BLACKJACK_POINT && hands.cards.size == 2)
            return Status.BLACKJACK

        if ((1..21).contains(hands.sumOfPoints()))
            return Status.HIT

        return Status.BUST
    }
    fun compareScore(otherPlayer: Player): Int =
        sumOfPoints().compareTo(otherPlayer.sumOfPoints())

    companion object {
        private const val BLACKJACK_POINT = 21
    }
}
