package blackjack.model

class Card(private val denominationToShape: Pair<Denomination, Shape>) {

    override fun toString(): String = "${denominationToShape.first.symbol}${denominationToShape.second.symbol}"

    fun getDenomination(): Denomination =
        Denomination.findBySymbol(denominationToShape.first.symbol)
}
