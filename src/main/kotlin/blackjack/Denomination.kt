package blackjack

enum class Denomination(
    val score: List<Int>,
) {

    ACE(listOf(1, 11)),
    JACK(listOf(10)),
    QUEEN(listOf(10)),
    KING(listOf(10)),
}
