package blackjack.domain

class Denominations(private val denomination: List<Denomination>) {
    val sumScore = Score(denomination.sumOf { it.score.number })
}

enum class Denomination(
    val label: String,
    val score: Score,
    val extraScore: Score
) {
    ACE("Ace", Score(1), Score(11)),
    TWO("2", Score(2), Score(0)),
    THREE("3", Score(3), Score(0)),
    FOUR("4", Score(4), Score(0)),
    FIVE("5", Score(5), Score(0)),
    SIX("6", Score(6), Score(0)),
    SEVEN("7", Score(7), Score(0)),
    EIGHT("8", Score(8), Score(0)),
    NINE("9", Score(9), Score(0)),
    TEN("10", Score(10), Score(0)),
    JACK("Jack", Score(10), Score(0)),
    QUEEN("Queen", Score(10), Score(0)),
    KING("King", Score(10), Score(0))
    ;

    val isSingleScore = extraScore.number == 0
}
