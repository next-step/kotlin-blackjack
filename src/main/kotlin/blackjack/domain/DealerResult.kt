package blackjack.domain

data class DealerResult(private val playerCount: Int) {
    private val resultStatic = mutableMapOf(
        PlayResultType.WIN to 0, PlayResultType.LOSE to 0, PlayResultType.DRAW to 0
    )
    fun setStatic(_winCount: Int, _loseCount: Int) {
        resultStatic[PlayResultType.WIN] = _winCount
        resultStatic[PlayResultType.LOSE] = _loseCount
        resultStatic[PlayResultType.DRAW] = playerCount - _winCount - _loseCount
    }
    fun getStatic() = resultStatic.toMap()
}
