package blackject.model

/**
 * 참가자 정보 관리 클래스
 * */
open class Person(
    name: String,
    private var _amount: Amount? = null,
) : Player(name) {
    val amount: Amount
        get() = _amount ?: Amount(0.0)

    fun inputBetMoney(money: Double?) {
        require(money != null)
        _amount = Amount(money)
    }

    fun getProfit(): Int {
        return result.profit(amount).toInt()
    }

    fun getBetAmount(): Double = amount.value

    override fun calculateGameResult(winScore: Int?, isDealerBust: Boolean, isDealerBlackJack: Boolean) {
        val score = getScore()
        when {
            isDealerBust || isDealerBlackJack && cards.isBlackjack(score) -> changeResultType(ResultType.Win)
            cards.isBlackjack(score) -> changeResultType(ResultType.BlackJack)
            cards.isBust(score) -> changeResultType(ResultType.Bust)
            score == winScore -> changeResultType(ResultType.Win)
            else -> changeResultType(ResultType.Lose)
        }
    }
}
