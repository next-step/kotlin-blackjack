package blackjack.domain

enum class CardDenomination(
    val denomination: String,
    val value: List<Int>,
) {
    ACE("ACE", listOf(1, 11)),
    ONE("ONE", listOf(1)),
    TWO("TWO", listOf(2)),
    THREE("THREE", listOf(3)),
    FOUR("FOUR", listOf(4)),
    FIVE("FIVE", listOf(5)),
    SIX("SIX", listOf(6)),
    SEVEN("SEVEN", listOf(7)),
    EIGHT("EIGHT", listOf(8)),
    NINE("NINE", listOf(9)),
    TEN("TEN", listOf(10)),
    JACK("JACK", listOf(10)),
    QUEEN("QUEEN", listOf(10)),
    KING("KING", listOf(10)),
}
