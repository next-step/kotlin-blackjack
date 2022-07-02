package blackjack.domain

class Dealer : Player(DEALER_NAME) {

    companion object {
        const val DEALER_NAME = "딜러"
    }
}
