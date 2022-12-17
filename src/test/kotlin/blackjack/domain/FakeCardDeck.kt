package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType

class FakeCardDeck(
    private val shuffledCards: List<Card>,
    private val firstCard: Card = Card(CardType.ACE, CardShape.DIAMOND),
) : CardDeckPlay {

    override val cards: List<Card>
        get() = shuffledCards

    override fun shuffle() {
        // do nothing
    }

    override fun takeOutFirstCard(): Card = firstCard
}
