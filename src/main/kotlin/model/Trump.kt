package model

class Trump(
    private val porkerNumber: List<PokerNumber>,
    private val pokerShape: List<PokerShape>
) {
    var cards = getTrump()
        private set

    private fun getTrump(): MutableList<Card> {
        var cards = mutableListOf<Card>()
        porkerNumber.forEach { number ->
            pokerShape.forEach { shape ->
                cards.add(Card(number, shape))
            }
        }
        return cards.apply { shuffle() }
    }
}
