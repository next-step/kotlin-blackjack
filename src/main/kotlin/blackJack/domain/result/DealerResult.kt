package blackJack.domain.result

class DealerResult(
    private var _profit: Int = 0
) {

    val profit: Int
        get() = _profit

    fun sumProfit(profit: Int) {
        _profit += -profit
    }
}
