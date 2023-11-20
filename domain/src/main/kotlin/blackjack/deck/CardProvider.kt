package blackjack.deck

import blackjack.card.Card

interface CardProvider {
    fun provideCards(): List<Card>
}
