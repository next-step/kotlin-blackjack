package blackjack.domain

object BlackJackResultManager {
    fun getResult(dealer: Dealer, players: Players): BlackJackResult {
        val playersProfits = players.getPlayersToProfitMoney(
            getGameResult = { player -> player.getGameResultWith(dealer) },
            onSetPlayerProfitMoney = { profitMoney -> dealer.adjustProfit(profitMoney) }
        )
        return BlackJackResult(dealer.profitMoney, playersProfits)
    }
}

data class BlackJackResult(
    val dealerProfitMoney: ProfitMoney,
    val playerToProfit: PlayerToProfitMoney,
)

data class PlayerToProfitMoney(val value: Map<Player, ProfitMoney>)
