package blackjack.domain

const val LIMIT_HIT_NUMBER = 16
const val DEALER_NAME = "딜러"

class Dealer(name: String = DEALER_NAME, playerCount: Int = 0) : Player(name) {
    var dealerResult = DealerResult(playerCount)
    override fun calculatePoint(aceToBig: Boolean): Int =
        super.calculatePoint(super.calculatePoint(true) < BLACKJACK_POINT)

    override fun addCard(card: Card) {
        super.addCard(card)
        isHit = calculatePoint() <= LIMIT_HIT_NUMBER
    }
}
