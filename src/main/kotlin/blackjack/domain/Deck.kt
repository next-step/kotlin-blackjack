package blackjack.domain

class Deck {
    val cards: List<Card> = (1..52).map { Card(it) }
}
