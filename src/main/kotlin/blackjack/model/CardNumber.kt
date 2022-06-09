package blackjack.model

enum class CardNumber(val scores: List<Score>, val desc: String) {
    Ace(listOf(Score(1), Score(11)), "A"),
    Two(Score(2), "2"),
    Three(Score(3), "3"),
    Four(Score(4), "4"),
    Five(Score(5), "5"),
    Six(Score(6), "6"),
    Seven(Score(7), "7"),
    Eight(Score(8), "8"),
    Nine(Score(9), "9"),
    Ten(Score(10), "10"),
    Jack(Score(10), "J"),
    Queen(Score(10), "Q"),
    King(Score(10), "K");

    constructor(score: Score, desc: String) : this(listOf(score), desc)
}
