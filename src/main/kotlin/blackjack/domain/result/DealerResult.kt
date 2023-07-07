package blackjack.domain.result

data class DealerResult(val winCount: Int = 0, val loseCount: Int = 0) {
    fun dealerWin(): DealerResult {
        return copy(winCount = winCount + 1)
    }

    fun dealerLose(): DealerResult {
        return copy(loseCount = loseCount + 1)
    }
}
