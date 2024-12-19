package blackjack.domain

import java.math.BigDecimal

object BlackJackResultManager {
    fun getResult(dealer: Dealer, players: Players): BlackJackResult {
        val playersProfits = players.getPlayersToProfitMoney(
            dealer.isBlackJackInitially,
            dealer.cardsSum
        )
        return BlackJackResult(playersProfits)
    }
}

data class BlackJackResult(
    val playerToProfit: PlayerToProfitMoney,
) {
    val dealerProfitMoney: ProfitMoney get() = ProfitMoney().apply { set(-playerToProfit.getAllProfitSum) }
}

data class PlayerToProfitMoney(val value: Map<Player, ProfitMoney>) {
    val getAllProfitSum: BigDecimal get() = value.values.sumOf { it.getCurrentProfit() }
}
