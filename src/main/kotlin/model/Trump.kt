package model

class Trump(
    private val porkerNumber: List<PokerNumber> = PokerNumber.pokerNumbers(),
    private val pokerShape: List<PokerShape> = PokerShape.pokerShapes()
) {
    val cards = getTrump()

    private fun getTrump(): Cards {
        val cards =
            porkerNumber.flatMap { number ->
                pokerShape.map { shape -> Card(number, shape) }
            }.toMutableList()
        return Cards(cards.apply { shuffle() })
    }

    fun pick() = cards.pick()
    fun count() = cards.count()
}
