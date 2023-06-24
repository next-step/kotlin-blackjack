package blackjack.domain.deck

import blackjack.domain.card.Card

interface DeckShuffleStarategy {
    fun shuffleDeck(deck: MutableList<Card>)
}