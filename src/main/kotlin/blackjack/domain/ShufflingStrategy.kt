package blackjack.domain

import java.util.*

interface ShufflingStrategy {
    fun shuffle(cards: Collection<Card>): Queue<Card>

    object NoShuffling : ShufflingStrategy {
        override fun shuffle(cards: Collection<Card>): Queue<Card> {
            return LinkedList(cards)
        }
    }

    object RandomShuffling : ShufflingStrategy {
        override fun shuffle(cards: Collection<Card>): Queue<Card> {
            return LinkedList(cards.shuffled())
        }
    }
}
