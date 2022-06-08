package blackjack.domain


data class Participant(
    val name: String,
    val playerCards: Cards = Cards()
) {
    val status: List<GameResult>
        get() = _status.toList()

    private val _status: MutableList<GameResult> = mutableListOf()

    fun addStatus(isWin: List<GameResult>) = this._status.addAll(isWin)
    fun addCard(card: Card) {
        this.playerCards.addCard(card)
    }
}
