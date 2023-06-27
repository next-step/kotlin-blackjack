package blackjack.domain.deck

import blackjack.domain.card.Card
import kotlin.random.Random

class RandomDeckShuffleStrategy : DeckShuffleStarategy {
    override fun shuffleDeck(deck: MutableList<Card>): List<Card> {
        return deck.shuffled(Random(System.currentTimeMillis()))
    }
}