package blackjack.domain

object GameResultManager {
    fun calculate(dealer: Dealer, players: List<Player>): GameResult {

        val playerResults: List<PlayerResult> = players.map {
            if (it.total() < dealer.total() || it.status == PlayerStatus.BUST) PlayerResult(it, Result.LOSE)
            else if (it.total() > dealer.total()) PlayerResult(it, Result.WIN)
            else PlayerResult(it, Result.DRAW)
        }

        return GameResult(
            DealerResult(
                playerResults.count { it.result == Result.LOSE },
                playerResults.count { it.result == Result.DRAW },
                playerResults.count { it.result == Result.WIN }
            ),
            playerResults)
    }
}

data class GameResult(val dealerResult: DealerResult, val playerResults: List<PlayerResult>)
data class DealerResult(val win: Int, val draw: Int, val lose: Int)
data class PlayerResult(val player: Player, val result: Result)
enum class Result(val displayName: String) {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
}
