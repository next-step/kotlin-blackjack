package blackjack.domain.game

import blackjack.domain.game.Profit.Companion.sum
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.Player

data class GamerResult(val profit: Profit, val gamer: Gamer) {

    constructor(result: GameResult.Type, player: Player) : this(result.calculateProfit(player.bet), player)

    val name = gamer.name

    companion object {

        fun getDealerResultFromPlayers(dealer: Dealer, playerResults: List<GamerResult>): GamerResult {
            val dealerProfit = playerResults.map { it.profit }.sum().negative()
            return GamerResult(dealerProfit, dealer)
        }
    }
}
