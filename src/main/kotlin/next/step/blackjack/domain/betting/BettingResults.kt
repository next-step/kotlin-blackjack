package next.step.blackjack.domain.betting

data class BettingResults(val playersResult: Map<String, Int>, val dealerResult: Int) {
    companion object {
        fun from(playersResult: Map<String, Int>): BettingResults =
            BettingResults(playersResult, dealerResult(playersResult))

        private fun dealerResult(playersResult: Map<String, Int>): Int =
            playersResult.map { -it.value }.sum()
    }
}