package blackjack.domain

@JvmInline
value class GameResult private constructor(private val _gameResultMap: Map<GameResultState, Int>) {

    val gameResultMap: Map<GameResultState, Int>
        get() = _gameResultMap.toMap()

    companion object {
        fun from(resultMap: Map<GameResultState, Int>): GameResult {
            return GameResult(resultMap.toMap())
        }
    }
}
