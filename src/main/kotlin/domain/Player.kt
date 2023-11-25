package domain

import enum.GameResult

class Player(val name: String) : AbstractCardHolder() {
    private var matchResult: GameResult = GameResult.DRAW
    val result: GameResult
        get() = matchResult

    fun determineResult(dealerScore: Int) {
        matchResult = GameResult.determineForResultOfPlayer(calculateScore(), dealerScore)
    }
}
