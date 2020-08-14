package blackjack

abstract class Gamer(val name: String) {

    private val _myCards = mutableListOf<Card>()
    val myCards: List<Card> get() = _myCards

    override fun toString(): String = name

    fun requestCard(card: Card) {
        _myCards.add(card)
    }

    fun calculatePoint(): Int =
        _myCards
            .map { it.getDenomination().point }
            .reduce { acc, point ->
                val accIfAce = if (Denomination.isAce(acc)) acc + EXTRA_ACE_POINT else acc
                accIfAce + point + if (Denomination.isAce(point) && accIfAce + point + EXTRA_ACE_POINT <= MAX_POINT) EXTRA_ACE_POINT else 0
            }

    companion object {
        private const val EXTRA_ACE_POINT = 10
        private const val MAX_POINT = 21
    }
}
