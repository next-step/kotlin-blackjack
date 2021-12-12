package blackjack.domain.card

import java.util.LinkedList
import java.util.Queue

data class CardDeck(private val cardQueue: Queue<Card> = LinkedList(CARD_SET.shuffled())) : Queue<Card> by cardQueue {

    fun next(): Card {
        if (cardQueue.isEmpty()) {
            resetQueue()
        }
        return cardQueue.poll()
    }

    private fun resetQueue() {
        cardQueue.clear()
        cardQueue.addAll(CARD_SET.shuffled())
    }

    companion object {
        private val ALL_CARDS = Denomination
            .values()
            .flatMap(Companion::generateSuitNumberPair)
            .associateWith { Card(it.first, it.second) }

        private fun generateSuitNumberPair(denomination: Denomination): List<Pair<SuitType, Denomination>> = SuitType
            .values()
            .map { suitType ->
                suitType to denomination
            }

        val CARD_SET = ALL_CARDS.values.toSet()
    }
}
