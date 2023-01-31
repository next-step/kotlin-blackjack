package model

open class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    private val gameResult = GameResult(0, 0, 0)
    val sumOfCardNumber get() = CardNumberCalculator.sumCardNumbers(cards)
    val cards: List<Card> get() = _cards.toList()

    fun receiveCard(card: Card): Boolean {
        return _cards.add(card)
    }

    fun notifyGameResultState(): GameResult {
        return gameResult
    }

    fun updateGameResultState(result: GameResultState) {
        when (result) {
            GameResultState.WIN -> gameResult.win++
            GameResultState.LOSE -> gameResult.lose++
            GameResultState.DRAW -> gameResult.draw++
        }
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
