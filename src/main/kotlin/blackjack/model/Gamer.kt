package blackjack.model

abstract class Gamer(val name: String) {

    private val _myReceivedCard = mutableListOf<Card>()
    val myReceivedCard: List<Card> get() = _myReceivedCard

    fun calculatePoint(): Point =
        _myReceivedCard
            .map { it.getCardKinds().point }
            .map { Point(it) }
            .reduce { acc, point -> reducePoint(acc, point) }

    fun requestCard(card: Card) {
        _myReceivedCard.add(card)
    }

    private fun reducePoint(acc: Point, point: Point): Point {
        val firstAceCaseConsideredAcc = Point.calculateAccIfAceFirst(acc)
        return firstAceCaseConsideredAcc + point + calculateExtraPoint(firstAceCaseConsideredAcc, point)
    }

    private fun calculateExtraPoint(acc: Point, point: Point): Point =
        if (Point.isAvailableExtraPoint(acc, point)) Point.ACE_EXTRA_POINT else Point(0)
}
