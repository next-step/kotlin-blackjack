package blackjack.model

class Card {
    enum class Type(val nickName: String, val points: List<Int>) {
        ACE("A", listOf(1, 11)),
        ONE("1", listOf(1)),
        TWO("2", listOf(2)),
        THREE("3", listOf(3)),
        FOUR("4", listOf(4)),
        FIVE("5", listOf(5)),
        SIX("6", listOf(6)),
        SEVEN("7", listOf(7)),
        EIGHT("8", listOf(8)),
        NINE("9", listOf(9)),
        TEN("10", listOf(10)),
        KING("K", listOf(10)),
        QUEEN("Q", listOf(10)),
        JACK("J", listOf(10));

        companion object {
            fun findByNickname(nickName: String): Type = values().first { it.nickName == nickName }
        }
    }

    enum class Shape(val nickName: String) {
        DIAMOND("다이아"),
        CLOVER("클로버"),
        HEART("하트"),
        SPADE("스페이드");
    }
}
