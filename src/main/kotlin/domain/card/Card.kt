package domain.card

import domain.player.Player.Companion.WIN_SCORE


data class Card(val symbol: Symbol, val suit: Suit) {

    override fun toString(): String {
        return "${symbol.symbolName}${suit.suitName}"
    }

    enum class Symbol(val symbolName: String, val score: Int) {
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
        KING("K", 10),
        ACE("A", 11);

        fun isAce(): Boolean = this == ACE
        fun reCalculateAce(score: Int): Int = if (score > WIN_SCORE) score - AMOUNT_TO_LOW else score
    }

    enum class Suit(val suitName: String) {
        SPADE("스페이드"),
        HEART("하트"),
        DIAMOND("다이아몬드"),
        CLUB("클로버")
    }

    companion object {
        private const val ACE_VALUE_LOW = 1
        private const val ACE_VALUE_HIGH = 11
        const val AMOUNT_TO_LOW = ACE_VALUE_HIGH - ACE_VALUE_LOW
    }
}

