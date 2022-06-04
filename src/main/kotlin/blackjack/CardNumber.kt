package blackjack

data class CardNumber(private val number: Int) {
    init {
        validate(number)
    }

    private fun validate(number: Int) = require(number in 1..13) { "유효하지 않은 카드 입니다" }
}
