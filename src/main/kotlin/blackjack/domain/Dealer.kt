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

    fun calculateProfit(playerProfit: Int) {
        profit -= playerProfit
    }

    override val canHit: Boolean
        get() = getScore() < DEALER_MIN_SCORE && !isBusted

    fun compare(player: Player): MatchResult {
        if (player.isBlackjack && player.cards.cards.size == 2) {
            return MatchResult.BLACKJACK_WIN
        }
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
        private const val DEALER_MIN_SCORE = 17
    }
}
