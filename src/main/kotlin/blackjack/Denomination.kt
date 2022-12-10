package blackjack

enum class Denomination(val value: String, val score: Int) {
    ACE("A", 0),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    KING("K", 10),
    QUEEN("Q", 10);

    companion object {
        private const val ACE_MIN_SCORE = 1
        private const val ACE_MAX_SCORE = 11

        fun sum(denominations: List<Denomination>): Int {
            var sum = denominations.sumOf { it.score }
            if (denominations.contains(ACE)) {
                sum = addAceScore(sum)
            }
            return sum
        }

        private fun addAceScore(sum: Int): Int {
            val addedAceMaxScore = sum + ACE_MAX_SCORE
            return if (addedAceMaxScore > 21) {
                sum + ACE_MIN_SCORE
            } else {
                addedAceMaxScore
            }
        }
    }
}
