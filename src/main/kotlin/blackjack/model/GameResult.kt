package blackjack.model

enum class GameResult {
    WIN,
    PUSH,
    LOSE,
    ;
}

sealed class GamePlayerResult {
    abstract val name: String

    data class Dealer(
        override val name: String,
        val win: Int,
        val lose: Int,
        val push: Int,
    ) : GamePlayerResult()

    data class Player(override val name: String, val gameResult: GameResult) : GamePlayerResult()
}
