package blackjack.domain

class Dealer : Player(DEALER_NAME) {
    override val canTakeCards: Boolean
        get() = getTotalScore() <= DEALER_SCORE_THRESHOLD

    override infix fun wins(player: Player): Boolean {
        if (isBusted) return false
        if (player.isBusted) return true
        return getTotalScore() > player.getTotalScore()
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_SCORE_THRESHOLD = 16
    }
}
