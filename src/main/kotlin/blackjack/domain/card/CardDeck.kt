package blackjack.domain.card

import blackjack.domain.shuffle.Shuffler
import java.util.LinkedList

@JvmInline
value class CardDeck private constructor(val cards: LinkedList<Card>) {

    fun poll(): Card {
        return cards.poll()
    }

    companion object {
        fun create(shuffler: Shuffler<Card>): CardDeck {
            return CardDeck(LinkedList(shuffler.shuffled(Card.ALL_CARDS)))
        }
    }
}
