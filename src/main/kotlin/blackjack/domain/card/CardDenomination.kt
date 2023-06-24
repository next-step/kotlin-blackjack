package blackjack.domain.card

enum class CardDenomination(
    val score: CardScoreType,
) {
    ACE(CardScoreType.flexible()),
    TWO(CardScoreType.fixed(2)),
    THREE(CardScoreType.fixed(3)),
    FOUR(CardScoreType.fixed(4)),
    FIVE(CardScoreType.fixed(5)),
    SIX(CardScoreType.fixed(6)),
    SEVEN(CardScoreType.fixed(7)),
    EIGHT(CardScoreType.fixed(8)),
    NINE(CardScoreType.fixed(9)),
    JACK(CardScoreType.fixed(10)),
    QUEEN(CardScoreType.fixed(10)),
    KING(CardScoreType.fixed(10)),
    ;

    companion object {

        val ALL_CARD_DENOMINATIONS = CardDenomination.values()
    }
}
