package blackjack.domain

class Dealer(results: List<ResultStatus> = emptyList()) : Player("딜러") {
    private var _results: MutableList<ResultStatus> = results.toMutableList()
    val results: List<ResultStatus>
        get() = _results.toList()

    fun getMatchResult(player: Player): ResultStatus {
        if (this.isBust()) return ResultStatus.WIN
        if (player.isBust()) return ResultStatus.LOSE
        val result = player.score match this.score
        calculateResult(result)

        return result
    }

    fun isHit() = this.score <= DEALER_HIT_SCORE

    fun calculateResult(result: ResultStatus) {
        val dealerResult = when (result) {
            ResultStatus.WIN -> ResultStatus.LOSE
            ResultStatus.LOSE -> ResultStatus.WIN
            else -> ResultStatus.DRAW
        }
        _results.add(dealerResult)
    }

    companion object {
        private const val DEALER_HIT_SCORE = 16
    }
}
