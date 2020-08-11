package blackjack.domain

const val LIMIT_HIT_NUMBER = 16
const val DEALER_NAME = "딜러"

class Dealer(name: String = DEALER_NAME) : Player(name) {
    override var isHit: Boolean = true
        get() = calculatePoint() <= LIMIT_HIT_NUMBER

    fun calculatePoint(): Int {
        return super.calculatePoint(super.calculatePoint(true) < BLACKJACK_POINT)
    }
}
