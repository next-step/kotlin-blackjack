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
        val playersProfits = players.getPlayersToProfitMoney(dealer)
        return BlackJackResultV2(dealer.profitMoney, playersProfits)
    }
}

private fun Players.getPlayersToProfitMoney(dealer: Dealer): PlayerToProfitMoney {
    val map =
        value.associateWith { player ->
            player.setPlayersProfitMoney(
                result = GameResult.fromScores(dealer.cardsSum, player.cardsSum),
                isDealerBlackJackInitially = dealer.isBlackJackInitially,
            )

            val playerProfitMoney = player.profitMoney
            dealer.adjustProfit(playerProfitMoney)
            playerProfitMoney
        }
    return PlayerToProfitMoney(map)
}

private fun Player.setPlayersProfitMoney(
    result: GameResult,
    isDealerBlackJackInitially: Boolean,
) {
    when {
        this.isBlackJackInitially && isDealerBlackJackInitially.not() -> this.onBlackJackInitially()
        this.isBlackJackInitially && isDealerBlackJackInitially -> this.onPush()
        result.isWin() -> this.onWin()
        result.isBust() -> this.onBust()
        result.isLose() -> this.onLose()
        result.isPush() -> this.onPush()
    }
}

data class BlackJackResultV2(
    val dealerProfitMoney: ProfitMoney,
    val playerToProfit: PlayerToProfitMoney,
)

data class PlayerToProfitMoney(val value: Map<Player, ProfitMoney>)

enum class GameResult {
    WIN,
    BUST,
    LOSE,
    PUSH,
    ;

    fun isWin() = this == WIN

    fun isBust() = this == BUST

    fun isLose() = this == LOSE

    fun isPush() = this == PUSH

    companion object {
        fun fromScores(
            dealerScore: Int,
            playerScore: Int,
        ): GameResult {
            return when {
                dealerScore > Card.MAX_SUM -> WIN // Dealer bust
                playerScore > Card.MAX_SUM -> BUST // Player bust
                dealerScore > playerScore -> LOSE
                playerScore > dealerScore -> WIN
                else -> PUSH
            }
        }
    }
}

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
