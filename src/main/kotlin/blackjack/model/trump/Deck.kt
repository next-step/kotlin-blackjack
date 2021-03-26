package blackjack.model.trump

class Deck internal constructor(
    private val deck: Map<Suit, DeckWithSameSuit> = Suit.values().associate { Pair(it, DeckWithSameSuit(it)) }
) : Map<Suit, DeckWithSameSuit> by deck
