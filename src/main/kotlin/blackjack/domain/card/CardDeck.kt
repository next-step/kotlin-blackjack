package blackjack.domain.card

import blackjack.domain.shuffle.Shuffler
import java.util.LinkedList

@JvmInline
value class CardDeck(private val cards: LinkedList<Card>) {

    constructor(cards: List<Card>) : this(LinkedList(cards))

    fun poll(): Card {
        return cards.poll()
    }

    companion object {

        fun createAllCards(shuffler: Shuffler<Card>): CardDeck {
            return CardDeck(shuffler.shuffled(Card.ALL_CARDS))
        }
    }
}
