package blackjack.domain

import blackjack.model.Card

class FakeCardDeck(
    private val shuffledCards: List<Card>,
) : CardDeckPlay {

    override val cards: List<Card>
        get() = shuffledCards

    override fun shuffle() {
        // do nothing
    }

    override fun takeFirstCard(): Card {
        TODO("Not yet implemented")
    }

    fun aa() {}
}
