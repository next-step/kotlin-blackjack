package blackjack.model

data class BlackjackRevenueJudge(
    private val dealer: BlackjackDealer,
    private val players: Collection<BlackjackPlayer>,
) {
    val dealerRevenue: Money = players
        .map { dealer.revenueFrom(it) }
        .reduce(Money::plus)

    val playerResults: Collection<BlackjackPlayerResult> =
        players.map { BlackjackPlayerResult(it, -dealer.revenueFrom(it)) }

    private fun BlackjackDealer.revenueFrom(player: BlackjackPlayer): Money {
        if (isWinThan(player)) {
            return player.bettingMoney
        }
        return player.bettingMoney.negative
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
