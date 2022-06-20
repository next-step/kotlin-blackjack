package blackjack.model.card

data class Card(
    private val symbol: CardSymbol,
    private val number: CardNumber,
) {

    val score
        get() = number.score

    override fun toString() = "${number.mark}$symbol"
}
