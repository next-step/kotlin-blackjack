package blackjack.model.trump

class DeckWithSameSuit private constructor(private val deckWithSameSuit: Map<CardNumber, Card>) : Map<CardNumber, Card> by deckWithSameSuit {
    constructor(suit: Suit) : this(CardNumber.values().associate { Pair(it, Card(it, suit)) })
}
