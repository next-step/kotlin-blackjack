package blackjack.dto

object Deck {
    private val cards = mutableListOf<Card>()

    init {
        Suit.values().forEach { suit ->
            Number.values().forEach { number ->
                cards.add(Card(suit, number))
            }
        }
    }

    fun newDeck() = cards.shuffled()
}
