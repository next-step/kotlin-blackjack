package blackjack

data class Card(private val card: Pair<Denomination, Suit>) {


    fun getScore(pair: Pair<Denomination, Suit>): Int {
        return Denomination.getScore(pair.first)
    }

    override fun toString(): String {
        return card.first.name + card.second.shapeName
    }

}
