package blackjack.model

private const val ZERO = 0

class GameResult(
    private val dealerResult: Map<DualResult, Int>,
    private val playersResult: Map<Player, DualResult>
) {
    fun getDealerResultCountOf(dualResult: DualResult): Int? {
        return dealerResult[dualResult]
    }

    fun getPlayerDualResultOf(player: Player): DualResult {
        return playersResult[player] ?: throw IllegalArgumentException("해당 플레이어의 게임 결과가 없습니다.")
    }

    companion object {
        fun of(dealer: Player, players: Players): GameResult {
            val dealerResult = players.map { dealer.wins(it) }
                .groupingBy { it }
                .eachCount()
            val playersResult = players.associateWith { it.wins(dealer) }

            return GameResult(dealerResult, playersResult)
        }
    }
}
