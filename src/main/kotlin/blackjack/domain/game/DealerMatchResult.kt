package blackjack.domain.game

data class DealerMatchResult(
    val winCount: Int,
    val tieCount: Int,
    val loseCount: Int,
)
