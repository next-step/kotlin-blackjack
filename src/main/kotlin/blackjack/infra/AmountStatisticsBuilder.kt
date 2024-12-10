package blackjack.infra

import blackjack.domain.Money
import blackjack.domain.Player
import blackjack.domain.Statistics
import blackjack.domain.StatisticsBuilder

data class AmountStatistics(
    val dealerProfit: Money,
    val playerProfits: Map<String, Money>,
) : Statistics

class AmountStatisticsBuilder : StatisticsBuilder<AmountStatistics>() {
    private var dealerProfit = Money.ZERO
    private val playerProfits = mutableMapOf<String, Money>()

    override fun onWin(player: Player) {
        when (player.isBlackJack()) {
            true -> {
                val betAmount = player.evenMoney()
                dealerProfit -= player.profit(betAmount)
                addPlayerProfit(player.name, betAmount)
            }
            false -> {
                addPlayerProfit(player.name, player.bettingMoney)
            }
        }
    }

    override fun onLose(player: Player) {
        val betAmount = player.bettingMoney
        dealerProfit += betAmount
        addPlayerProfit(player.name, betAmount.toNegative())
    }

    override fun onDraw(player: Player) {
        playerProfits[player.name] = playerProfits.getOrDefault(player.name, player.bettingMoney)
    }

    private fun addPlayerProfit(
        playerName: String,
        amount: Money,
    ) {
        playerProfits[playerName] = playerProfits.getOrDefault(playerName, Money.ZERO) + amount
    }

    override fun build(): AmountStatistics {
        return AmountStatistics(dealerProfit, playerProfits)
    }
}
