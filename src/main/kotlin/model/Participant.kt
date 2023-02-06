package model

open class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()
    val sumOfCardNumber get() = CardNumberCalculator.sumCardNumbers(cards)
    private var _gameResultState = GameResultState.DRAW
    val gameResultState get() = _gameResultState

    fun receiveCard(card: Card): Boolean {
        return _cards.add(card)
    }

    fun updateGameResult(result: GameResultState) {
        _gameResultState = result
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
