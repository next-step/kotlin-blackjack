package blackjack.domain

data class DealerResult(
    val dealer: Dealer,
    private val gamblerResults: List<GamblerResult>,
) {
    private val resultStatusCounts =
        gamblerResults.groupingBy { gamblerResult ->
            gamblerResult.resultStatus
        }.eachCount()

    val winCount: Int
        get() = resultStatusCounts[ResultStatus.DEFEAT] ?: 0

    val drawCount: Int
        get() = resultStatusCounts[ResultStatus.DRAW] ?: 0

    val defeatCount: Int
        get() = resultStatusCounts[ResultStatus.WIN] ?: 0
}
