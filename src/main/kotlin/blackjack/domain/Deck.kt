package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.ShuffleMachine
import java.util.LinkedList
import java.util.Queue

class Deck(
    shuffleMachine: ShuffleMachine
) {
    private val cards: Queue<Card>

    init {
        cards = LinkedList(shuffleMachine.shuffle(Card.CARDS))
    }

    fun draw(): Card {
        return cards.poll()
    }
}
