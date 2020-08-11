package blackjack.domain

private const val LIMIT_HIT_NUMBER = 17
private const val DEALER_NAME = "딜러"

class Dealer(name: String = DEALER_NAME) : Player(name) {
    override var isHit: Boolean = calculatePoint() < LIMIT_HIT_NUMBER
}
