class CardNumber private constructor(private val number: String) {

    companion object {
        private const val ERR_MSG_UNKNOWN_NUMBER = "알 수 없는 숫자입니다."
        private val CARD_NUMBER_LIST = listOf(
            "A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "J", "K", "Q"
        )

        private val NUMBERS: Map<String, CardNumber> = CARD_NUMBER_LIST.associateWith(::CardNumber)

        fun from(value: String): CardNumber {
            return NUMBERS[value] ?: throw IllegalArgumentException(ERR_MSG_UNKNOWN_NUMBER)
        }
    }
}
