package blackjack.domain.card

data class Card(
    val pattern: CardPattern,
    val denomination: CardDenomination,
) {
    fun getOrder(): Int = denomination.order
    fun getValue(sum: Int): Int = denomination.getValue(sum)
}
