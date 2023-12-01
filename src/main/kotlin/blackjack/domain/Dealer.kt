package blackjack.domain

class Dealer() : BlackjackParticipant(DEALER_NAME) {
    fun getCard(deck: Deck): Int {
        var count = 0
        while (canHit) {
            hit(deck.draw())
            count++
        }
        return count
    }

    override val canHit: Boolean
        get() = getScore() < DEALER_TARGET_SCORE

    fun compare(player: Player): MatchResult {
        if (this.isBusted) return MatchResult.WIN
        if (player.isBusted) return MatchResult.LOSS
        val playerScore = player.getScore()
        return when {
            this.getScore() == playerScore -> MatchResult.TIE
            playerScore > this.getScore() -> MatchResult.WIN
            else -> MatchResult.LOSS
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_TARGET_SCORE = 17
    }
}
