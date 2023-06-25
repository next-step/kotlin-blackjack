package blackjack.card.signature

enum class CardOrdinalSignature(val code: String, val values: List<Int>) {
    ACE("A", listOf(1, 11)),
    TWO("2", listOf(2)),
    THREE("3", listOf(3)),
    FOUR("4", listOf(4)),
    FIVE("5", listOf(5)),
    SIX("6", listOf(6)),
    SEVEN("7", listOf(7)),
    EIGHT("8", listOf(8)),
    NINE("9", listOf(9)),
    TEN("10", listOf(10)),
    J("J", listOf(10)),
    Q("Q", listOf(10)),
    K("K", listOf(10)),
    ;

    companion object {
        fun pickRandomOne() = values().toMutableList().shuffled().first()
    }
}
