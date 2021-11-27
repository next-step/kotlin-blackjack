package blackJack.domain.player

class BettingMoney {

    private var _money: Int = ZERO

    val money: Int
        get() = _money

    fun inputMoney(bettingMoney: Int) {
        _money = bettingMoney
    }

    companion object {
        private const val ZERO = 0
    }
}
