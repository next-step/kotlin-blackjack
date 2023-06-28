package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.deck.DeckShuffleStarategy

class NotRandomDeckShuffleStrategy : DeckShuffleStarategy {
    override fun shuffleDeck(deck: MutableList<Card>): List<Card> {
        return deck
    }
}
