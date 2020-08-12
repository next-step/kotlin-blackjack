package blackjack.model

abstract class Gamer(val name: String) {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    fun calculatePoint(): Point =
        _myReceivedCard
            .map { it.getCardKinds().point }
            .map { Point(it) }
            .reduce { acc, point ->
                val firstAceCaseConsideredAcc = Point.calculateAccIfAceFirst(acc)
                firstAceCaseConsideredAcc +
                    point +
                    if (Point.isAvailableExtraPoint(firstAceCaseConsideredAcc, point)) Point.ACE_EXTRA_POINT else Point(
                        0
                    )
            }

    fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    companion object {
        const val MAX_POINT = 21
        protected const val ACE_EXTRA_POINT = 10
    }
}
