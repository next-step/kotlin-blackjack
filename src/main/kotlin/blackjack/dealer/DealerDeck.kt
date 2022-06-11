package blackjack.dealer

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardShape
import java.util.LinkedList

class DealerDeck {
    private var cards: LinkedList<Card> = LinkedList(
        CardNumber.values().flatMap { number ->
            CardShape.values().map { shape -> Card(number, shape) }
        }
            .shuffled()
    )

    fun draw(): Card {
        return this.cards.pop()
    }

    fun size(): Int {
        return this.cards.size
    }
}
