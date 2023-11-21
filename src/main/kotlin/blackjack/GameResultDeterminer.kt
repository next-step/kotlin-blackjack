package blackjack

import java.util.EnumMap

object GameResultDeterminer {
    fun getResult(players: List<Player>, dealer: Dealer): GameResult {
        val dealerResult: EnumMap<MatchResult, Int> = EnumMap(MatchResult::class.java)
        val playerResult = mutableListOf<PlayerResult>()
        for (player in players) {
            val result = player vs dealer
            playerResult.add(PlayerResult(player.name, result))
            dealerResult[result.opponentResult] = dealerResult.getOrDefault(result.opponentResult, 0) + 1
        }

        return GameResult(dealerResult, playerResult)
    }
}

data class GameResult(
    val dealerResult: EnumMap<MatchResult, Int>,
    val playerResult: List<PlayerResult>,
)

data class PlayerResult(val name: String, val matchResult: MatchResult)
