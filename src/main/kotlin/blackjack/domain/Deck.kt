package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class Deck(
    shuffler: CardShuffler
) {
    private val cards: Queue<Card>

    init {
        cards = LinkedList(shuffler.shuffle(Card.CARDS))
    }

    fun draw(): Card {
        return cards.poll()
    }
}
