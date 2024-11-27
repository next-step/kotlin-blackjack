package blackjack

class Deck {
    val cards: List<Card> =
        Suit.entries.flatMap { suit ->
            Rank.entries.map { rank -> Card(suit, rank) }
        }.shuffled()
}
