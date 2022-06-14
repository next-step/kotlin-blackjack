package blackjack.domain

enum class CardNumber(val symbol: String, val score: Score) {
    Ace("A", Score(1)),
    Num2("2", Score(2)),
    Num3("3", Score(3)),
    Num4("4", Score(4)),
    Num5("5", Score(5)),
    Num6("6", Score(6)),
    Num7("7", Score(7)),
    Num8("8", Score(8)),
    Num9("9", Score(9)),
    Num10("10", Score(10)),
    Jack("J", Score(10)),
    Queen("Q", Score(10)),
    King("K", Score(10))
}
