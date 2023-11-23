class Suit private constructor(private val suit: String) {

    companion object {
        private const val ERR_MSG_UNKNOWN_SUIT = "알 수 없는 무늬 입니다."
        private val CARD_SUIT_LIST = listOf("Spade", "Heart", "Diamond", "Club")

        private val SUIT: Map<String, Suit> = CARD_SUIT_LIST.associateWith(::Suit)

        fun from(value: String): Suit {
            return SUIT[value] ?: throw IllegalArgumentException(ERR_MSG_UNKNOWN_SUIT)
        }
    }
}
