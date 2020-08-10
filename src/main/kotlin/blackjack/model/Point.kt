package blackjack.model

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
