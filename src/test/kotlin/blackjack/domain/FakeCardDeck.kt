package blackjack.domain

import blackjack.model.Card

class FakeCardDeck(
    private val shuffledCards: List<Card>,
) : CardDeckPlay {

    override val cards: List<Card>
        get() = shuffledCards

    override fun shuffle(): List<Card> = shuffledCards

    override fun takeFirstCard(): Card {
        TODO("Not yet implemented")
    }
}
