package blackjack.model

class Card(private val denomination: Denomination, private val shape: Shape) {

    override fun toString(): String = "${denomination.symbol}${shape.symbol}"

    fun getDenomination(): Denomination =
        Denomination.findBySymbol(denomination.symbol)
}
