package blackjack

data class Card(private val card: Pair<Denomination, Suit>) {


    fun getScore(): Int {
        return Denomination.getScore(card.first)
    }

    override fun toString(): String {
        return card.first.name + card.second.shapeName
    }

}
