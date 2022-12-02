package model

class Trump(
    private val porkerNumber: List<PokerNumber>,
    private val pokerShape: List<PokerShape>
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
}
