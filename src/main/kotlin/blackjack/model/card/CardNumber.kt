package blackjack.model.card

enum class CardNumber(
    val mark: String,
    val score: CardScore,
) {

    TWO("2", CardScore(2, 2)),
    THREE("3", CardScore(3, 3)),
    FOUR("4", CardScore(4, 4)),
    FIVE("5", CardScore(5, 5)),
    SIX("6", CardScore(6, 6)),
    SEVEN("7", CardScore(7, 7)),
    EIGHT("8", CardScore(8, 8)),
    NINE("9", CardScore(9, 9)),
    TEN("10", CardScore(10, 10)),
    KING("K", CardScore(10, 10)),
    QUEEN("Q", CardScore(10, 10)),
    JACK("J", CardScore(10, 10)),
    ACE("A", CardScore(1, 11)),
}
