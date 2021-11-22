package blackject.model

/**
 * 블랙잭 게임에 참가하는 플레이어
 * */
open class Player(
    var amount: Amount? = null
) {
    open fun inputBetMoney(money: Double?) {
        require(money != null)
        amount = Amount(money)
    }
}
