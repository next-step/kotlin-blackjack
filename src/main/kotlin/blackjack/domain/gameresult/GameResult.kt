package blackjack.domain.gameresult

import blackjack.domain.GameProfit.GameProfit

class GameResult(
    val name: String,
    val gameProfits: List<GameProfit>
) {
    constructor(
        name: String,
        gameProfit: GameProfit
    ) : this(name, listOf(gameProfit))

    fun sumGameProfits(): GameProfit {
        return gameProfits.reduce(GameProfit::plus)
    }
}
