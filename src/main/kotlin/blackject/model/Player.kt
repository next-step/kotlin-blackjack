package blackject.model

/**
 * 블랙잭 게임에 참가하는 플레이어
 * */
open class Player(
    private var _result: ResultType? = null,
    private var _amount: Amount = Amount(0.0)
) {

    val amount: Amount
        get() = _amount

    val result: ResultType
        get() = _result ?: ResultType.Lose

    open fun getProfit(): Int {
        return result.profit(amount).toInt()
    }

    open fun inputBetMoney(money: Double?) {
        require(money != null)
        _amount = Amount(money)
    }

    fun changeResultType(result: ResultType) {
        this._result = result
    }
}
