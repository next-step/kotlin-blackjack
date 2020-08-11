package blackjack.model.player

const val DEALER_NAME = "딜러"
const val DEALER_CALL_MAX_POINT = 16
const val DEALER_CALL_MAX_CARD = 3

class Dealer : Player(name = DEALER_NAME) {
    override fun call(): Boolean {
        return cards.sumByPoint() <= DEALER_CALL_MAX_POINT && cards.getCount() <= DEALER_CALL_MAX_CARD
    }
}
