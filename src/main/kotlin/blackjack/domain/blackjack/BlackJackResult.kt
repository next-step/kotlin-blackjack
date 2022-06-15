package blackjack.domain.blackjack

import blackjack.domain.common.Money
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players

data class BlackJackResult(
    private val dealer: Dealer,
    private val players: Players,
) {

    fun dealerProfit(): ParticipantProfitResult {
        return ParticipantProfitResult(dealer.name, dealer.profit(players))
    }

    fun playersProfit(): List<ParticipantProfitResult> {
        val profitByPlayer = players.profit(dealer)
        return profitByPlayer.map { (player, profit) -> ParticipantProfitResult(player.name, profit) }
    }
}

data class ParticipantProfitResult(
    val name: String,
    val profit: Money,
)
