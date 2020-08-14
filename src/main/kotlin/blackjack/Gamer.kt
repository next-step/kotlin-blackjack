package blackjack

abstract class Gamer(val name: String) {

    private val _myCards = mutableListOf<Card>()
    val myCards: List<Card> get() = _myCards

    override fun toString(): String = name

    fun requestCard(card: Card) {
        _myCards.add(card)
    }

    fun calculatePoint(): Point =
        _myCards.map { it.getDenomination().point }
            .map(::Point)
            .reduce(::reducePoint)

    private fun reducePoint(acc: Point, point: Point): Point {
        val calculatedAccIfAceFirst = Point.calculateIfAceFirst(acc)
        return calculatedAccIfAceFirst + point + Point.calculateIfExtraPointExist(calculatedAccIfAceFirst, point)
    }
}
