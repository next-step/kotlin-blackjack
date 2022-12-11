package blackjack.application

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.strategy.DeckGenerateStrategy

data class Deck(private val deckGenerateStrategy: DeckGenerateStrategy) {
    private val cards: PlayingCards = deckGenerateStrategy.generate()

    fun getCard(): PlayingCard {
        return cards.get()
    }

    fun getCardsByNumberOfCards(numberOfCards: Int): PlayingCards {
        return PlayingCards((1..numberOfCards).map { cards.get() })
    }
}
