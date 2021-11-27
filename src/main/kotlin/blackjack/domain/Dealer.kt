package blackjack.domain

class Dealer : Player(DEALER_NAME) {
    override val canTakeCards: Boolean
        get() = getTotalScore() <= DEALER_SCORE_THRESHOLD

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_SCORE_THRESHOLD = 16
    }
}
