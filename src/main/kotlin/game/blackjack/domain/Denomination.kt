package game.blackjack.domain

enum class Denomination(val symbol: String, val score: Int) {
    ACE("A", 1),
    TWO("2", 2),
    TREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ;

    companion object {

        private const val BLACKJACK_SCORE = 21
        private const val ACE_EXTRA_SCORE = 10

        fun score(ranks: List<Denomination>): Int {
            var total = ranks.sumOf { it.score }
            total += if (ranks.contains(ACE) && total + ACE_EXTRA_SCORE <= BLACKJACK_SCORE) ACE_EXTRA_SCORE else 0
            return total
        }

        fun isBust(score: Int): Boolean = score > BLACKJACK_SCORE
    }
}
