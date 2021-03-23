package blackjack.domain.card

import blackjack.domain.Card
import blackjack.domain.CardShape
import blackjack.domain.CardType

class DefaultCardFactory : CardFactory {
    override fun createCards(): MutableList<Card> {
        return createAllShapeCards().shuffled().toMutableList()
    }

    private fun createAllShapeCards(): MutableList<Card> {
        val cards = mutableListOf<Card>()
        CardShape.values().forEach { cardShape ->
            createSingleShapeCardsOf(cardShape, cards)
        }
        return cards
    }

    private fun createSingleShapeCardsOf(cardShape: CardShape, cards: MutableList<Card>) {
        CardType.values().forEach { cardType ->
            cards.add(Card(cardShape, cardType))
        }
    }

}
