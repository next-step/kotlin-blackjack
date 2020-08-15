package blackjack.model

data class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return "${suit.name} ${Denomination.initial(denomination)}"
    }
}

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum class Denomination(private val initial: String, private val score: Int) {
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
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    companion object {
        private const val ACE_VALUE_1 = 1
        private const val ACE_VALUE_11 = 11

        fun initial(denomination: Denomination) = denomination.initial
        fun score(denomination: Denomination) = denomination.score
        fun sum(totalScore: Int, denomination: Denomination): Int {
            if (score(denomination) == ACE_VALUE_1) {
                return sumWithAce(totalScore)
            }
            return totalScore + score(denomination)
        }

        private fun sumWithAce(totalScore: Int): Int {
            if (totalScore + ACE_VALUE_11 < 21) {
                return totalScore + ACE_VALUE_11
            }
            return totalScore + ACE_VALUE_1
        }
    }
}
