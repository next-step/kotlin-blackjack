package blackJack.domain

class Result(players: Players, dealer: Dealer) {
    private val _dealerResult: MutableMap<String, Int> = mutableMapOf("수익" to DEFAULT_PROFIT)
    val dealerResult
        get() = _dealerResult.toMap()
    val playerResult = players.makeMap {}

    companion object {
        const val DEFAULT_PROFIT = 0
    }
}
