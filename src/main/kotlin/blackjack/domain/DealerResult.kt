package blackjack.domain

data class DealerResult(private val playerCount: Int) {
    private val resultStatic = mutableMapOf(
        PlayResultType.WIN to 0, PlayResultType.LOSE to 0, PlayResultType.DRAW to 0
    )
    var profitMoney: Int = 0
        private set

    fun setStatic(_winCount: Int, _loseCount: Int, _onlyPlayers: Sequence<Player>) {
        resultStatic[PlayResultType.WIN] = _winCount
        resultStatic[PlayResultType.LOSE] = _loseCount
        resultStatic[PlayResultType.DRAW] = playerCount - _winCount - _loseCount
        profitMoney = _onlyPlayers.sumBy { it.getProfitMoney() }.times(-1)
    }

    fun getStatic() = resultStatic.toMap()
}
