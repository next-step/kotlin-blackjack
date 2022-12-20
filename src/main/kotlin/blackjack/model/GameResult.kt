package blackjack.model

enum class GameResult {
    WIN,
    PUSH,
    LOSE,
    ;
}

sealed class PlayerGameResult {
    abstract val name: String

    data class Dealer(
        override val name: String,
        val win: Int,
        val lose: Int,
        val push: Int,
    ) : PlayerGameResult()

    data class Player(override val name: String, val gameResult: GameResult) : PlayerGameResult()
}

data class PlayerGameResults(val value: List<PlayerGameResult>)
