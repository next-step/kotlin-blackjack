package blackjack.domain.card

import java.util.LinkedList
import java.util.Queue

class RandomDrawStrategy : DrawStrategy {

    private val cards: Queue<Card> = LinkedList<Card>(Card.ALL.shuffled())

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
