package step2.domain

object CardDeckGenerator {

    fun generateCardDeck(): Cards {
        return Cards(CardShape.values().flatMap { generateCard(it) }.toMutableSet())
    }

    private fun generateCard(shape: CardShape): List<Card> {
        return CardScore.values()
            .map { Card(shape, it) }
    }
}
