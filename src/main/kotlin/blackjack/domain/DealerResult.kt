package blackjack.domain

data class DealerResult(
    val dealer: Dealer,
    val winCount: Int,
    val defeatCount: Int,
)
