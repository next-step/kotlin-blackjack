package blackjack.domain

class Deck : DrawStrategy {
    private val cards = mutableListOf<Card>()

    override fun fetchCard(): Card {
        return Card.of(Symbol.SPADE, Denomination.ACE)
    }
}
