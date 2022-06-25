package blackjack.model

class Dealer : Player(DEALER_NAME) {

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
