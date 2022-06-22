package blackjack.domain.player

import blackjack.domain.game.GameStatus

class GambleSummary {
    var gameStatus: GameStatus = GameStatus.DRAW
    var battingAmount: Int = 0
    var earningRate: Double = 1.0
}
