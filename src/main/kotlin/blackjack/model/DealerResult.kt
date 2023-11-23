package blackjack.model

data class DealerResult(
    val point: Int,
    val winning: Int,
    val drawing: Int,
    val losing: Int,
)
