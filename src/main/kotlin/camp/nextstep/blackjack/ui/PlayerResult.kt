package camp.nextstep.blackjack.ui

import camp.nextstep.blackjack.game.GameResult

class PlayerResult(val result: GameResult) {

    override fun toString(): String {
        return when (result) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
            else -> "무"
        }
    }
}
