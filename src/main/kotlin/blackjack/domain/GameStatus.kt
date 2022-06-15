package blackjack.domain

import blackjack.domain.dto.ProfitResult

data class GameStatus(
    val dealer: Dealer,
    val players: List<Player>,
) {

    fun toProfitResult(): List<ProfitResult> {
        val players = players.map { ProfitResult(it.name, it.profit) }
        val dealer = ProfitResult(dealer.name, -players.sumOf { it.profit })

        return listOf(dealer) + players
    }

    private val Player.profit: Double
        get() = GameResult
            .from(dealer.score, score)
            .getProfit(hand.betAmount.toDouble())
}
