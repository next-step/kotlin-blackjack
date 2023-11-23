package blackjack.domain

object Deck {
    private val INITIAL_CARDS: List<Card> = Suit.values()
        .flatMap { suit ->
            Denomination.values()
                .map { denomination ->
                    Card(denomination, suit)
                }
        }
        .shuffled()

    private val cards: MutableList<Card> = INITIAL_CARDS.toMutableList()

    fun draw(): Card {
        if (cards.isEmpty()) {
            cards.addAll(INITIAL_CARDS)
        }
        return cards.removeLast()
    }
}
