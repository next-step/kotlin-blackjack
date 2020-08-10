package blackjack.model

class Card {
    enum class Kinds(val kindsName: String) {
        ACE("A"),
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        KING("K"),
        QUEEN("Q"),
        JACK("J");
    }

    enum class Shape(val shapeName: String) {
        DIAMOND("다이아"),
        CLOVER("클로버"),
        HEART("하트"),
        SPADE("스페이드");
    }

    enum class Point(val points: Int, val kinds: Array<Kinds>) {
        ONE(1, arrayOf(Kinds.ACE, Kinds.ONE)),
        TWO(2, arrayOf(Kinds.TWO)),
        THREE(3, arrayOf(Kinds.THREE)),
        FOUR(4, arrayOf(Kinds.FOUR)),
        FIVE(5, arrayOf(Kinds.FIVE)),
        SIX(6, arrayOf(Kinds.SIX)),
        SEVEN(7, arrayOf(Kinds.SEVEN)),
        EIGHT(8, arrayOf(Kinds.EIGHT)),
        NINE(9, arrayOf(Kinds.NINE)),
        TEN(10, arrayOf(Kinds.TEN, Kinds.KING, Kinds.QUEEN, Kinds.JACK)),
        ELEVEN(11, arrayOf(Kinds.ACE));

        companion object {
            fun findByKinds(kinds: Kinds): List<Point> = values().filter { it.kinds.contains(kinds) }
        }
    }
}
