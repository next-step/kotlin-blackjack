package blackjack.domain.card.type

import blackjack.domain.Score

enum class Denomination(
    val description: String,
    val score: List<Score>
) {
    ACE("A", listOf(Score(1), Score(11))),
    TWO("2", listOf(Score(2))),
    THREE("3", listOf(Score(3))),
    FOUR("4", listOf(Score(4))),
    FIVE("5", listOf(Score(5))),
    SIX("6", listOf(Score(6))),
    SEVEN("7", listOf(Score(7))),
    EIGHT("8", listOf(Score(8))),
    NINE("9", listOf(Score(9))),
    TEN("10", listOf(Score(10))),
    JACK("J", listOf(Score(10))),
    QUEEN("Q", listOf(Score(10))),
    KING("K", listOf(Score(10)));
}
