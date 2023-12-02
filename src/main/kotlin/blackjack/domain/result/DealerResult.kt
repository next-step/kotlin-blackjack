package blackjack.domain.result

data class DealerResult(
    val win: Int = 0,
    val draw: Int = 0,
    val lose: Int = 0,
) {
    fun set(competeResult: CompeteResult): DealerResult {
        return when (competeResult) {
            CompeteResult.WIN -> copy(win = win + 1)
            CompeteResult.DRAW -> copy(draw = draw + 1)
            CompeteResult.LOSE -> copy(lose = lose + 1)
        }
    }
}
