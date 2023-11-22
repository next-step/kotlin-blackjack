package blackJack.domain

class CardDeck private constructor(val cards: Cards) {
    companion object {
        fun createShuffledDeck(): Cards {
            val cardDeck = Suit.values().flatMap { suit ->
                Rank.values().map { rank ->
                    Card(suit, rank)
                }
            }

            return Cards(cardDeck.shuffled().toMutableList())
        }
    }
}
