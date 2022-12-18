package blackjack.domain

class Dealer(results: List<ResultStatus> = emptyList()) : Player("딜러") {
    private var _results: MutableList<ResultStatus> = results.toMutableList()
    val results: List<ResultStatus>
        get() = _results.toList()

    fun getMatchResult(player: Player): ResultStatus {
        val playerResult = getPlayerResult(player)
        calculateResult(playerResult)

        return playerResult
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

    private fun getPlayerResult(player: Player): ResultStatus {
        if (this.isBlackJack()) return getPlayerResultWhenDealerBlackJack(player)
        if (this.isBust()) return ResultStatus.WIN
        return player.score match this.score
    }

    private fun getPlayerResultWhenDealerBlackJack(player: Player): ResultStatus {
        if (player.isBlackJack()) return ResultStatus.DRAW
        return ResultStatus.LOSE
    }

    companion object {
        private const val DEALER_HIT_SCORE = 16
    }
}
