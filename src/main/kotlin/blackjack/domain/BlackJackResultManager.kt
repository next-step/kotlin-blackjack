package blackjack.domain

class BlackJackResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun getResult(): BlackJackResult {
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
