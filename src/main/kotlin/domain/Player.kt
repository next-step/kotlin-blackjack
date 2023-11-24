package domain

import enum.GameResult

class Player(val name: String) : AbstractCardHolder() {
    var result: GameResult = GameResult.DRAW
}
