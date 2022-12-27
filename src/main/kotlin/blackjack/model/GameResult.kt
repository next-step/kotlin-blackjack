package blackjack.model

enum class GameResult {
    WIN,
    PUSH,
    LOSE,
    BLACKJACK,
    PUSH_WITH_BLACKJACK,
    ;
}

sealed class PlayerGameResult {
    abstract val name: String
    abstract val profit: Double

    data class Dealer(
        override val name: String,
        override val profit: Double = 0.0,
    ) : PlayerGameResult()

    data class Player(
        override val name: String,
        override val profit: Double = 0.0,
    ) : PlayerGameResult()
}

data class PlayerGameResults(val value: List<PlayerGameResult>)
