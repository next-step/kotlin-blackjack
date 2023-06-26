package blackjack.model

data class BlackjackRevenueJudge(
    private val dealer: BlackjackDealer,
    private val players: Collection<BlackjackPlayer>,
) {
    val dealerRevenue: Money = players
        .map { -it.revenue(dealer) }
        .reduce(Money::plus)

    val playerResults: Collection<BlackjackPlayerResult> =
        players.map { BlackjackPlayerResult(it, it.revenue(dealer)) }

    private fun BlackjackPlayer.revenue(dealer: BlackjackDealer): Money {
        if (isSameLimitScore) {
            return revenueBlackjack(dealer)
        }
        if (isWinFrom(dealer)) {
            return bettingMoney
        }
        return bettingMoney.negative
    }

    private fun BlackjackPlayer.revenueBlackjack(dealer: BlackjackDealer): Money {
        return if (dealer.isSameLimitScore) {
            Money.ZERO
        } else if (deckCount == BONUS_REVENUE_CARD_COUNT) {
            bettingMoney.oneAndHalfTimes
        } else {
            bettingMoney
        }
    }

    private fun BlackjackPlayer.isWinFrom(dealer: BlackjackDealer): Boolean {
        if (dealer.isScoreOverThanLimitScore) {
            return false
        }
        if (isScoreOverThanLimitScore) {
            return true
        }
        return isWinByScoreGap(dealer)
    }

    companion object {
        private const val BONUS_REVENUE_CARD_COUNT = 2
    }
}
