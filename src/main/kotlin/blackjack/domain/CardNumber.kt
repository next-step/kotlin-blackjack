package blackjack.domain

enum class CardNumber(val display: String, val value: Int) {
    ACE("A", 11),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2),
    ONE("1", 1)
    ;

    companion object {
        private val RANGE = 1..11

        fun findNumber(display: String): Int {
            val number = find(display)
            require(RANGE.contains(number)) { "카드 범위에서 벗어난 수 입니다." }
            return number
        }

        private fun find(display: String) = CardNumber.values().find { display == it.display }?.value ?: 0
    }
}
