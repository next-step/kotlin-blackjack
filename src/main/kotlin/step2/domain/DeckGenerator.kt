package step2.domain

object DeckGenerator {

    fun generateCardDeck(): Cards {
        return Cards(Suit.values().flatMap { generateCard(it) }.toMutableSet())
    }

    private fun generateCard(suit: Suit): List<Card> {
        return Denomination.values()
            .map { Card(suit, it) }
    }
}
