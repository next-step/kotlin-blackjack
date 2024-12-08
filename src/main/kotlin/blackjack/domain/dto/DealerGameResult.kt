package blackjack.domain.dto

data class DealerGameResult(
    val winCount: Int,
    val lossCount: Int,
    val drawCount: Int,
)
