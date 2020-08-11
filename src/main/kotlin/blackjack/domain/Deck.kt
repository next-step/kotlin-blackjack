package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class Deck : DrawStrategy {
    private val cards: Queue<Card> = LinkedList<Card>()

    init {
        cards.addAll(Card.ALL.shuffled())
    }

    override fun fetchCard(): Card {
        checkCardsAreEmpty()
        return cards.poll()
    }

    private fun checkCardsAreEmpty() {
        if (this.cards.isEmpty()) {
            cards.addAll(Card.ALL.shuffled())
        }
    }
}
