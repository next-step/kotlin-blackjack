package blackjack.model

abstract class Gamer {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    open fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    fun calculatePoint(): Int =
        _myReceivedCard
            .map { it.getCardKinds().point }
            .reduce { acc, point ->
                acc + point + if (isAvailableExtraPoint(acc, point)) ACE_EXTRA_POINT else 0
            }

    fun isReachMaxPoint() = calculatePoint() >= MAX_POINT

    private fun isAvailableExtraPoint(acc: Int, point: Int): Boolean {
        return point == Kinds.ACE.point && acc + point + ACE_EXTRA_POINT <= MAX_POINT
    }

    companion object {
        protected const val MAX_POINT = 21
        protected const val ACE_EXTRA_POINT = 10
    }
}
