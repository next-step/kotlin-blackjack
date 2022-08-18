package blackjack.domain.gameresult

class GameResults(
    val name: String,
    val result: Map<GameResult, Int>,
) {
    constructor(name: String, result: GameResult) : this(
        name = name,
        result = mapOf(result to 1)
    )
}
