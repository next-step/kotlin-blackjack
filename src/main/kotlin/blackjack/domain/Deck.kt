package blackjack.domain

class Deck {
    val cards: List<Card> = INITIAL_CARDS

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
