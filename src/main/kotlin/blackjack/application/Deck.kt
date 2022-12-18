package blackjack.application

import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards

@JvmInline
value class Deck(private val cards: PlayingCards) {
    fun getCard(): PlayingCard {
        return cards.get()
    }

    fun getCardsByNumberOfCards(numberOfCards: Int): PlayingCards {
        return PlayingCards.of((1..numberOfCards).map { cards.get() })
    }
}
