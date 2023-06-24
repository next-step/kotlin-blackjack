package blackjack.domain

class Deck(deck: List<Card>) {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        cards.addAll(deck)
    }

    fun getCards() = cards

    companion object {
        fun make(): Deck {
            val shuffledDeck = Denomination.values()
                .flatMap { denomination ->
                    Shape.values().map { shape ->
                        Card(denomination, shape)
                    }
                }.shuffled()
            return Deck(shuffledDeck)
        }
    }
}
