package blackjack.vo

import blackjack.domain.GameResult

class PlayerGameResultVO(
    val name: String,
    val gameResult: GameResult,
) {
    fun gameResult(): String {
        return when(gameResult) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
            GameResult.TIE -> "무"
        }
    }
}
