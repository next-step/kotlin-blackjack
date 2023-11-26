package domain

import enum.GameResult

class Player(val name: String) : AbstractCardHolder() {
    var result: GameResult = GameResult.DRAW
        private set

    fun determineResult(dealerScore: Int) {
        result = GameResult.determineForResultOfPlayer(calculateScore(), dealerScore)
    }
}
