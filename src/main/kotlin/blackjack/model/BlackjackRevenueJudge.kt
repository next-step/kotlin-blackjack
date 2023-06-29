package blackjack.model

import blackjack.model.participant.BlackjackDealer
import blackjack.model.participant.BlackjackPlayer

data class BlackjackRevenueJudge(
    private val dealer: BlackjackDealer,
    private val players: Collection<BlackjackPlayer>,
) {
    val dealerRevenue: Money = players
        .map { -it.revenue(dealer) }
        .reduce(Money::plus)

    val playerResults: Collection<BlackjackPlayerResult> =
        players.map { BlackjackPlayerResult(it, it.revenue(dealer)) }
}
