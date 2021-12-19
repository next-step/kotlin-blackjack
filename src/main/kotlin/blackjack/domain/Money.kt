package blackjack.domain

@JvmInline
value class Money(val money: Int) {

    init {
        require(money >= 0) { MONEY_ILLEGAL_ARGUMENTS_EXCEPTION_MESSAGE.format(money) }
    }

    companion object {
        const val MONEY_ILLEGAL_ARGUMENTS_EXCEPTION_MESSAGE = "money는 0이상이어야 합니다. 현재 값: %s"
        const val MONEY_NUMBER_FORMAT_EXCEPTION_MESSAGE = "money에는 정수를 입력하셔야 합니다."

        fun from(string: String): Money {
            return Money(string.toIntOrNull() ?: throw NumberFormatException(MONEY_NUMBER_FORMAT_EXCEPTION_MESSAGE))
        }
    }
}
