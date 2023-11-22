package blackjack.deck

import blackjack.card.Card

class FakeCardProvider : CardProvider {
    override fun provideCards(): List<Card> {
        return emptyList()
    }
}
