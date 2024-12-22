package blackjack.domain

data class DealerResult(
    val dealer: Dealer,  // todo 3개 이상의 인스턴스 변수를 가지면 안됨
    private val gamblerResults: List<GamblerResult>,
) {
    private val resultStatusCounts = gamblerResults.groupingBy { gamblerResult ->
        gamblerResult.resultStatus
    }.eachCount()

    val winCount: Int
        get() = resultStatusCounts[ResultStatus.DEFEAT] ?: 0

    val drawCount: Int
        get() = resultStatusCounts[ResultStatus.DRAW] ?: 0

    val defeatCount: Int
        get() = resultStatusCounts[ResultStatus.WIN] ?: 0
}
