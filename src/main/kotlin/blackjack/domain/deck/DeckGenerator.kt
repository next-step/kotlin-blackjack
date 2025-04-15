package blackjack.domain.deck

import blackjack.domain.card.Card

interface DeckGenerator {
    fun generate(): List<Card>
}

object RandomDeckGenerator : DeckGenerator {
    override fun generate() = Card.cached.shuffled()
}
