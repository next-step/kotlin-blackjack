package blackject.model

/**
 * 블랙잭 게임에 참가하는 플레이어
 * */
open class Player(
    private var _amount: Amount? = null
) {

    val amount: Amount
        get() = _amount ?: Amount()

    open fun inputBetMoney(money: Double?) {
        require(money != null)
        _amount = Amount(money)
    }
}
