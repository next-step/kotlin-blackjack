package blackjack.domain

class Deck {
    private val cards: MutableList<Card> = INITIAL_CARDS.toMutableList()

    fun draw(): Card {
        if (cards.isEmpty()) {
            cards.addAll(INITIAL_CARDS)
        }
        return cards.removeLast()
    }

    companion object {
        private val INITIAL_CARDS: List<Card> = Suit.values()
            .flatMap { suit ->
                Denomination.values()
                    .map { denomination ->
                        Card(denomination, suit)
                    }
            }
            .shuffled()
    }
}
