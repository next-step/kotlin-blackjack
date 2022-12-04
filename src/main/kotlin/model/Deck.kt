package model

class Deck {
    val cards = getDeck()

    private fun getDeck(): Cards {
        val cards =
            PokerNumber.values().flatMap { number ->
                PokerShape.values().map { shape -> Card(number, shape) }
            }.toMutableList()
        return Cards(cards.apply { shuffle() })
    }

    fun pick() = cards.pick()
    fun count() = cards.count()
}
