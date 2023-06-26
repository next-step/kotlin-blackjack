package blackjack.model

data class BlackjackJudge(private val dealer: BlackjackDealer, private val players: Collection<BlackjackPlayer>) {

    val dealerWinCount: Int
        get() {
            return players.count { dealer.isWinThan(it) }
        }

    val dealerLoseCount: Int
        get() {
            return players.count() - dealerWinCount
        }

    val playerResults: Collection<BlackjackPlayerResult>
        get() {
            return players.map { BlackjackPlayerResult(it, !dealer.isWinThan(it)) }
        }

    private fun BlackjackDealer.isWinThan(participant: BlackjackParticipant): Boolean {
        if (isScoreOverThanLimitScore) {
            return false
        }
        if (participant.isScoreOverThanLimitScore) {
            return true
        }
        return (HandDeck.LIMIT_SCORE - deckScore) < (HandDeck.LIMIT_SCORE - participant.deckScore)
    }
}
