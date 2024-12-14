package blackjack.domain

class BlackJackResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun getResult(): BlackJackResult {
        val dealerScore = dealer.cardsSum
        val playersWinLose =
            players.value.associateWith { player ->
                val playerScore = player.cardsSum
                when {
                    dealerScore > 21 -> Result.WIN
                    playerScore > 21 -> Result.LOSE
                    playerScore > dealerScore -> Result.WIN
                    else -> Result.LOSE
                }
            }

        val dealerWinCount = playersWinLose.count { it.value == Result.LOSE }
        val dealerLoseCount = playersWinLose.count { it.value == Result.WIN }

        return BlackJackResult(dealerWinCount, dealerLoseCount, PlayerToResultMap(playersWinLose))
    }
}

data class BlackJackResult(
    val dealerWinCount: Int,
    val dealerLoseCount: Int,
    val playerToResultMap: PlayerToResultMap,
)

data class PlayerToResultMap(val value: Map<Player, Result>)

enum class Result {
    WIN,
    LOSE,
}
