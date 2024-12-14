package blackjack.domain

class BlackJackResultManager(
    private val dealer: Dealer,
    private val players: Players,
    private val playerResultCalculator: PlayerResultCalculator = PlayerResultCalculator(),
) {
    fun getResult(): BlackJackResult {
        val dealerScore = dealer.cardsSum
        val playersWinLose =
            players.value.associateWith { player ->
                playerResultCalculator.calculate(dealerScore, player.cardsSum)
            }

        val dealerWinCount = playersWinLose.count { it.value == PlayerResult.LOSE }
        val dealerLoseCount = playersWinLose.count { it.value == PlayerResult.WIN }
        return BlackJackResult(dealerWinCount, dealerLoseCount, PlayerToResultMap(playersWinLose))
    }
}

data class BlackJackResult(
    val dealerWinCount: Int,
    val dealerLoseCount: Int,
    val playerToResultMap: PlayerToResultMap,
)

@JvmInline
value class PlayerToResultMap(val value: Map<Player, PlayerResult>)

enum class PlayerResult {
    WIN,
    LOSE,
}
