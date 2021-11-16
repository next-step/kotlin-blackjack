package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

private typealias ResultType = GameResult.Type

object GamerResultFactory {

    private const val DEFAULT_COUNT = 0

    private val playerResults = listOf(
        PlayerBust,
        DealerBust,
        PlayerWin,
        PlayerDraw,
        PlayerLose,
    )

    fun getPlayerResult(dealer: Dealer, player: Player): PlayerResult {
        val result = playerResults.first { it.isApplicableTo(dealer, player) }
        return PlayerResult(result.getType(), player)
    }

    fun getDealerResult(playerResults: List<PlayerResult>): DealerResult {
        val dealerResultCache = playerResults.groupingBy { it.result.opposite() }
            .eachCount()
        return with(dealerResultCache) {
            DealerResult(
                win = getOrDefault(ResultType.WIN),
                draw = getOrDefault(ResultType.DRAW),
                lose = getOrDefault(ResultType.LOSE),
            )
        }
    }

    private fun ResultType.opposite(): ResultType {
        return when (this) {
            ResultType.WIN -> ResultType.LOSE
            ResultType.DRAW -> ResultType.DRAW
            ResultType.LOSE -> ResultType.WIN
        }
    }

    private fun Map<ResultType, Int>.getOrDefault(type: ResultType): Int {
        return get(type) ?: DEFAULT_COUNT
    }
}

