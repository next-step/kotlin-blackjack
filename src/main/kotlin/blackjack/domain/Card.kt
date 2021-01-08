package blackjack.domain

data class Card(private val card: Pair<Denomination, Suit>) {

    fun getScore(): Int {
        return Denomination.getScore(card.first)
    }

    fun checkAce(): Boolean {
        return card.first.name == "ACE"
    }

    override fun toString(): String {
        return Denomination.getSymbol(card.first) + card.second.shapeName
    }
}
