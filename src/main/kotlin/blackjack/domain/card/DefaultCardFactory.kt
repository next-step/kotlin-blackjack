package blackjack.domain.card

class DefaultCardFactory : CardFactory {
    override fun createCards(): List<Card> {
        return createAllShapeCards().shuffled().toMutableList()
    }

    private fun createAllShapeCards(): List<Card> {
        return CardShape.values().flatMap { cardShape ->
            CardType.values().map { cardType -> Card(cardShape, cardType) }
        }
    }
}
