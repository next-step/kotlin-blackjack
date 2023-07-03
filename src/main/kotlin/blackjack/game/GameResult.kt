package blackjack.game

class GameResult(private val matchResult: Map<String, Result>) {

    fun getResultForPlayer(playerName: String): Result? {
        return matchResult[playerName]
    }

    val getMatchResult: Map<String, Result>
        get() = matchResult
}
