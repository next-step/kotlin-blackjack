package blackjack.domain

class Dealer : Player(DEALER_NAME) {

    override val canNotHit: Boolean
        get() = sumOfPoints > MAX_HIT_POINT

    companion object {
        const val DEALER_NAME = "딜러"
        private const val MAX_HIT_POINT = 16
    }
}
