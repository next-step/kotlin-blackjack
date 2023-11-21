package blackjack_dealer.entity.result

data class DealerResult(
    val name: String = "딜러",
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val drawCount: Int = 0,
)
