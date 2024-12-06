package blackjack

class Deck(
    cards: List<Card>
) {
    private val cards = cards.toMutableList()

    fun draw(): Card = cards.removeAt(0)

    companion object {
        fun randomCardDeck() = Deck(
            CardSuit.entries.flatMap { suit ->
                CardNumber.entries.map { number -> Card(suit, number) }
            }
                .shuffled()
                .toMutableList()
        )
    }
}
