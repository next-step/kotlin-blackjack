package domain

data class Card(private val card: Pair<Suit, Denomination>) {

    fun score(): Int {
        return Denomination.getScore(card.second)
    }

    override fun toString(): String {
        return "${Denomination.getScore(card.second)}${card.first.value}"
    }
}
