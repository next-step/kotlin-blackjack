package blackjack.domain

class BlackJackResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    @Deprecated("step4에서는 getResultV2 를 사용하세요 - step3 에서만 사용됨")
    fun getResult(): BlackJackResult {
        val dealerScore = dealer.cardsSum
        val playersWinLose =
            players.value.associateWith { player ->
                PlayerResultCalculator.calculate(dealerScore, player.cardsSum)
            }

        return BlackJackResult(PlayerToResultMap(playersWinLose))
    }

    fun getResultV2(): BlackJackResultV2 {
        val playersProfits = players.getPlayersToProfitMoney(
            getGameResult = { player -> player.getGameResultWith(dealer) },
            onSetPlayerProfitMoney = { profitMoney -> dealer.adjustProfit(profitMoney) }
        )
        return BlackJackResultV2(dealer.profitMoney, playersProfits)
    }
}

data class BlackJackResultV2(
    val dealerProfitMoney: ProfitMoney,
    val playerToProfit: PlayerToProfitMoney,
)

data class PlayerToProfitMoney(val value: Map<Player, ProfitMoney>)

@Deprecated("deprecated - step3 에서만 사용됨")
data class BlackJackResult(
    val playerToResultMap: PlayerToResultMap,
) {
    val dealerWinCount: Int
        get() = playerToResultMap.getPlayerLoseCounts()
    val dealerLoseCount: Int
        get() = playerToResultMap.getPlayerWinningCounts()
}

@Deprecated("deprecated - step3 에서만 사용됨")
@JvmInline
value class PlayerToResultMap(val value: Map<Player, GameResult>) {
    fun getPlayerWinningCounts(): Int {
        return value.count { it.value == GameResult.WIN }
    }

    fun getPlayerLoseCounts(): Int {
        return value.count { it.value == GameResult.LOSE }
    }
}
