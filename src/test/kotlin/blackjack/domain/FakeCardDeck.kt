package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType

class FakeCardDeck(
    private val shuffledCards: List<Card>,
    private val firstCard: Card = Card(CardType.ACE, CardShape.DIAMOND),
) : CardDeck {

    override val cards: List<Card>
        get() = shuffledCards

    override val size: Int
        get() = cards.size

    override fun shuffle() {
        // do nothing
    }

    override fun takeOutFirstCard(): Card = firstCard
}
