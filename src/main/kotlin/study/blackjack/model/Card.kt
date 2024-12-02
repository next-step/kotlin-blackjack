package study.blackjack.model

/**
 * @author 이상준
 */
data class Card(
    val suit: Suit,
    val number: Int,
) {
    init {
        require(number in 1..13) { ERROR_CARD_NUMBER_MESSAGE }
    }

    fun name(): String {
        val number = when (number) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> number.toString()
        }
        return "$number${suit.suitName}"
    }

    fun score(isAce: Boolean = true): Int {
        return when {
            isAce && number == 1 -> 11
            number > 10 -> 10
            else -> number
        }
    }

    companion object {
        private const val ERROR_CARD_NUMBER_MESSAGE = "카드 숫자는 1부터 13까지 가능합니다."
    }
}
