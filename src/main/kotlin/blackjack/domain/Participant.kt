package blackjack.domain

data class Participant(
    val name: String,
    val playerCards: Cards = Cards(),
    val isDealer: Boolean = false
) {
    private val _gameResults: MutableList<GameResult> = mutableListOf()

    val gameResults: List<GameResult>
        get() = _gameResults.toList()

    fun addGameResult(isWin: List<GameResult>) = this._gameResults.addAll(isWin)
    fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }
}
