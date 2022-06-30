package blackjack.domain.deck

import java.util.LinkedList
import java.util.Queue

class Deck private constructor(
    val cards: Queue<Card>,
) {
    fun drawCard(): Card = cards.poll()
        ?: throw IllegalStateException("덱에 남은 카드가 없습니다.")

    companion object {
        fun release(): Deck = Deck(
            CardPattern.values()
                .flatMap { createCardWith(pattern = it) }
                .shuffled()
                .toCollection(LinkedList())
        )

        private fun createCardWith(pattern: CardPattern): List<Card> =
            CardNumber.values()
                .map { Card(pattern = pattern, number = it) }
    }
}
