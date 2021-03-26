package blackjack.domain.card

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType

class DefaultCardFactory : CardFactory {
    override fun createCards(): MutableList<Card> {
        return createAllShapeCards().shuffled().toMutableList()
    }

    private fun createAllShapeCards(): List<Card> {
        return CardShape.values().flatMap { cardShape ->
            CardType.values().map { cardType -> Card(cardShape, cardType) }
        }
    }
}
