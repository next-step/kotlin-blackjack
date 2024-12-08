package blackjack.core.player

class Dealer : Player(Name(DEALER_NAME)) {
    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
