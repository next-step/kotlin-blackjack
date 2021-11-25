package blackJack.domain.player

class BettingMoney(private var _money: Int = ZERO) {

    val money: Int
        get() = _money

    fun inputMoney(money: Int) {
        _money = money
    }

    companion object {
        private const val ZERO = 0
    }
}
