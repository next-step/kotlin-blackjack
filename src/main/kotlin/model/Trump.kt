package model

class Trump(
    private val porkerNumber: List<PokerNumber> = PokerNumber.pokerNumbers(),
    private val pokerShape: List<PokerShape> = PokerShape.pokerShapes()
) {
    val cards = getTrump()

    private fun getTrump(): Cards {
        val cards = mutableListOf<Card>()
        porkerNumber.forEach { number ->
            pokerShape.forEach { shape ->
                cards.add(Card(number, shape))
            }
        }
        return Cards(cards.apply { shuffle() })
    }

    fun pick() = cards.pick()
    fun count() = cards.count()
}
