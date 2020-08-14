package blackjack

class Card(private val denominationToShape: Pair<Denomination, Shape>) {

    override fun toString(): String = "${denominationToShape.first.symbol}${denominationToShape.second.symbol}"
}
