package blackjack.domain

data class Card(val suit: Suit, val number: Number) {

    enum class Suit(val value: String) {
        SPADE("스페이스"),
        HEART("하트"),
        DIAMOND("다이아몬드"),
        CLUB("클로버");
    }

    enum class Number(val symbol: String, val value: Int, val orValue: Int = 0) {
        ACE("A", 1, 11),
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
    }
}
