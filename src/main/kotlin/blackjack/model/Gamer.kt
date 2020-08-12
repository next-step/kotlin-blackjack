package blackjack.model

abstract class Gamer(val name: String) {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    fun isReachMaxPoint() = calculatePoint() >= MAX_POINT

    fun calculatePoint(): Int =
        _myReceivedCard
            .map { it.getCardKinds().point }
            .reduce { acc, point ->
                val firstAceCaseConsideredAcc = if (acc == Kinds.ACE.point) acc + ACE_EXTRA_POINT else acc

                firstAceCaseConsideredAcc +
                    point +
                    if (isAvailableExtraPoint(firstAceCaseConsideredAcc, point)) ACE_EXTRA_POINT else 0
            }

    open fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    private fun isAvailableExtraPoint(acc: Int, cardPoint: Int) =
        cardPoint == Kinds.ACE.point && acc + cardPoint + ACE_EXTRA_POINT <= MAX_POINT

    companion object {
        const val MAX_POINT = 21
        protected const val ACE_EXTRA_POINT = 10
    }
}
