package model

open class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()
    val sumOfCardNumber get() = CardNumberCalculator.sumCardNumbers(cards)
    private var _gameResultState = GameResultState.DRAW
    val gameResultState get() = _gameResultState
    val scoreState get() = ScoreStateGenerator().generate(sumOfCardNumber)
    var bettingMoney: Int = 0
        private set
    val profit: Int get() = ProfitCalculator().getProfitResult(this)

    fun receiveCard(card: Card): Boolean {
        return _cards.add(card)
    }

    fun updateGameResult(result: GameResultState) {
        _gameResultState = result
    }

    fun updateBettingMoney(money: Int) {
        bettingMoney = money
    }
}
