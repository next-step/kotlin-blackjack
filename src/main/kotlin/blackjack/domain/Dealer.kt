package blackjack.domain

class Dealer(results: List<ResultStatus> = emptyList()) : Player("딜러") {
    private var _results: MutableList<ResultStatus> = results.toMutableList()
    val results: List<ResultStatus>
        get() = _results.toList()

    fun getMatchResult(player: Player): ResultStatus {
        if (this.isBust()) return ResultStatus.WIN
        if (player.isBust()) return ResultStatus.LOSE
        val result = player.score match this.score
        addResult(result)

        return result
    }

    fun drawCard(card: Card) {
        this.cards.add(card)
    }

    private fun addResult(result: ResultStatus) {
        val dealerResult = when (result) {
            ResultStatus.WIN -> ResultStatus.LOSE
            ResultStatus.LOSE -> ResultStatus.WIN
            else -> ResultStatus.DRAW
        }
        _results.add(dealerResult)
    }
}
