package blackjack.domain.game

data class DealerMatchResult(
    val winCount: Int,
    val tieCount: Int,
    val loseCount: Int,
) {

    companion object {

        fun create(playerMatchResults: List<PlayerMatchResult>): DealerMatchResult {
            return DealerMatchResult(
                winCount = playerMatchResults.count { it.matchResultType.isLose() },
                tieCount = playerMatchResults.count { it.matchResultType.isTie() },
                loseCount = playerMatchResults.count { it.matchResultType.isWin() }
            )
        }
    }
}
