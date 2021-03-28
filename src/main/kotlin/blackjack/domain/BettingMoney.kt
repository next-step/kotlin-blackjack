package blackjack.domain

data class BettingMoney(val money: Int) {
    constructor(money: String) : this(money.toInt()) {
        validateNumber(money)
    }

    constructor(money: Double) : this(money.toInt())

    fun multiply(rate: Double) = BettingMoney(money * rate)

    private fun validateNumber(input: String) {
        requireNotNull(input.toIntOrNull()) { "배팅 금액은 숫자만 입력할 수 있습니다." }
    }
}