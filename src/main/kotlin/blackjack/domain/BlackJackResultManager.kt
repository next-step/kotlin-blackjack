package blackjack.domain

class BlackJackResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    @Deprecated("getResultV2 를 사용하세요")
    fun getResult(): BlackJackResult {
        val dealerScore = dealer.cardsSum
        val playersWinLose =
            players.value.associateWith { player ->
                PlayerResultCalculator.calculate(dealerScore, player.cardsSum)
            }

        return BlackJackResult(PlayerToResultMap(playersWinLose))
    }

    fun getResultV2(): BlackJackResultV2 {
        val playersToProfits =
            players.value.associateWith { player ->
                val result = GameResult.fromScores(dealer.cardsSum, player.cardsSum)
                when {
                    player.isBlackJackInitially && dealer.isBlackJackInitially.not() -> player.onBlackJackInitially()
                    player.isBlackJackInitially && dealer.isBlackJackInitially -> player.onPush()
                    result.isWin() -> player.onWin()
                    result.isBust() -> player.onBust()
                    result.isLose() -> player.onLose()
                    result.isPush() -> player.onPush()
                }

                player.profitMoney
            }
        playersToProfits.forEach { (_, profitMoney) ->
            dealer.adjustProfit(profitMoney)
        }
        return BlackJackResultV2(dealer.profitMoney, PlayerToProfitMoney(playersToProfits))
    }
}

data class BlackJackResultV2(
    val dealerProfitMoney: ProfitMoney,
    val playerToProfit: PlayerToProfitMoney,
)

data class PlayerToProfitMoney(
    val value: Map<Player, ProfitMoney>,
)

@Deprecated("deprecated")
data class BlackJackResult(
    val playerToResultMap: PlayerToResultMap,
) {
    val dealerWinCount: Int
        get() = playerToResultMap.getPlayerLoseCounts()
    val dealerLoseCount: Int
        get() = playerToResultMap.getPlayerWinningCounts()
}

@Deprecated("deprecated")
@JvmInline
value class PlayerToResultMap(val value: Map<Player, GameResult>) {
    fun getPlayerWinningCounts(): Int {
        return value.count { it.value == GameResult.WIN }
    }

    fun getPlayerLoseCounts(): Int {
        return value.count { it.value == GameResult.LOSE }
    }
}

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
