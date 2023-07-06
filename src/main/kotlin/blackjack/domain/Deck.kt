package blackjack.domain

class Deck {
    val cards: List<Card> = Suit.values().flatMap { suit ->
        Denomination.values().map { Card(suit, it) }
    }
}
