package blackjack.domain

import blackjack.model.Card

class FakeCardDeck(
    private val shuffledCards: List<Card>,
) : CardDeckPlay {

    override fun shuffle(): List<Card> {
        TODO("Not yet implemented")
    }

    override fun takeFirstCard(): Card {
        TODO("Not yet implemented")
    }
}
