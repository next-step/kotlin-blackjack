package blackjack

import blackjack.card.Card
import blackjack.card.deck.BlackJackCardDeck

class Dealer(
    private val cardDeck: BlackJackCardDeck
) {
    fun provideInitialCards(): List<Card> {
        return (0 until 2).map { cardDeck.castCard() }
    }

    fun provideCard(): Card {
        return cardDeck.castCard()
    }
}
