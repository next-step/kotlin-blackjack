package blackjack.domain

class Deck {
    val cards: List<Card> = Suit.values().flatMap { suit ->
        (1..13).map { Card(suit, it) }
    }
}
