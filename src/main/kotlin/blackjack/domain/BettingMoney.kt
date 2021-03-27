package blackjack.domain

class BettingMoney(input: String) {
    private val money: Int

    init {
        validateNumber(input)
        money = input.toInt()
    }

    private fun validateNumber(input: String) {
        requireNotNull(input.toIntOrNull()) { "배팅 금액은 숫자만 입력할 수 있습니다." }
    }
}