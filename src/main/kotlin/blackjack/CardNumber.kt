package blackjack

enum class CardNumber(val scores: List<Score>) {
    Ace(listOf(Score(1), Score(11))),
    Two(Score(2)),
    Three(Score(3)),
    Four(Score(4)),
    Five(Score(5)),
    Six(Score(6)),
    Seven(Score(7)),
    Eight(Score(8)),
    Nine(Score(9)),
    Ten(Score(10)),
    Jack(Score(10)),
    Queen(Score(10)),
    King(Score(10));

    constructor(score: Score) : this(listOf(score))
}
