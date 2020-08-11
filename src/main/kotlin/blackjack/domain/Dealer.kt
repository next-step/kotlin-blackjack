package blackjack.domain

private const val LIMIT_HIT_NUMBER = 17

class Dealer(name: String) : Player(name) {
    override var isHit: Boolean = calculatePoint() < LIMIT_HIT_NUMBER
}
