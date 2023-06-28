package blackjack.domain.game

data class GamerMatchResult(
    val dealerMatchResult: DealerMatchResult,
    val playerMatchResults: List<PlayerMatchResult>,
) {

    init {
        require(dealerMatchResult.loseCount == playerMatchResults.count { it.matchResultType.isWin() }) {
            "dealer lose count should be player total win count"
        }
        require(dealerMatchResult.winCount == playerMatchResults.count { it.matchResultType.isLose() }) {
            "dealer win count should be player total lose count"
        }
        require(dealerMatchResult.tieCount == playerMatchResults.count { it.matchResultType.isTie() }) {
            "dealer tie count should be player total tie count"
        }
    }
}
