package blackJack.domain.player

import java.math.BigDecimal

class BettingMoney(private var _money: BigDecimal = BigDecimal.ZERO) {

    val money: BigDecimal
        get() = _money

    fun inputMoney(money: BigDecimal) {
        _money = money
    }
}
