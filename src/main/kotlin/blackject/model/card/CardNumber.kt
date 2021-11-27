package blackject.model.card

/**
 * 카드 숫자 정보
 * */
enum class CardNumber(
    val numberName: String,
    val number: Int,
) {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    companion object {
        private const val PLUS_INT_ACE = 10
        val EXCEPT_NUMBER = ACE

        fun isAce(numberName: String): Boolean {
            return ACE.numberName == numberName
        }

        fun isRoyal(numberName: String): Boolean {
            return (KING.numberName == numberName) || (QUEEN.numberName == numberName) || (JACK.numberName == numberName)
        }

        fun number(number: CardNumber): Int = number.number

        fun getAceMaxNumber(): Int = ACE.number.plus(PLUS_INT_ACE)
    }
}
