package blackjack.model

data class Card(val suit: Suit, val denomination: Denomination) {
    override fun toString(): String {
        return "${suit.name} ${denomination.initial}"
    }
}

enum class Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES
}

enum class Denomination(val initial: String, val score: Int) {
    ACE("A", 11),
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
        private const val ACE_VALUE_GAP = 10
        fun calculate(score: Int): Int = if (score > WIN_SCORE) score - ACE_VALUE_GAP else score
    }
}
