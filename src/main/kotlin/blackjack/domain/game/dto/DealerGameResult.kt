package blackjack.domain.game.dto

data class DealerGameResult(
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int,
)
