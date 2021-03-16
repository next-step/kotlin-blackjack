package blackjack.domain

internal enum class CardNumber(val score: Int, val displayName: String = score.toString()) {

    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    companion object {
        fun score(numbers: List<CardNumber>): Int {
            val _numbers = mutableListOf(*numbers.toTypedArray())
            _numbers.remove(ACE)

            val aceNumberCount = numbers.size - _numbers.size
            var sum = _numbers.sumBy { it.score }

            repeat(aceNumberCount) {
                sum += if (sum + 11 <= BlackJackGame.MAX_SCORE) {
                    ACE_SECOND_SCORE
                } else {
                    ACE.score
                }
            }
            return sum
        }

        private const val ACE_SECOND_SCORE = 11
    }
}
