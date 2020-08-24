package blackJack.domain

class Deck() {
    var cards = DEFAULT_DECK
        private set

    fun getFirstCard(): Card {
        val card = cards[FIRST_CARD]
        deleteFirstCard()
        return card
    }

    private fun deleteFirstCard() {
        cards = cards.drop(DELETE_CARD)
    }

    fun shuffle(cards: List<Card>) {
        this.cards = cards.shuffled()
    }

    companion object {
        val DEFAULT_DECK = Shape.values().flatMap { shape -> Denomination.values().map { Card(shape, it) } }
        const val FIRST_CARD = 0
        const val DELETE_CARD = 1
    }
}
