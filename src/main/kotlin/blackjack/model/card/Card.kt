package blackjack.model.card

data class Card(
    val symbol: CardSymbol,
    private val number: CardNumber,
) {

    val score
        get() = number.score

    val numberMark
        get() = number.mark
}
