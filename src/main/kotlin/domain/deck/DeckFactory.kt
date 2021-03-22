package domain.deck

import domain.Card
import domain.Denomination
import domain.Suit

object DeckFactory {
    private val cards: List<Card> = Suit.values()
        .flatMap { suit ->
            Denomination.values()
                .map {
                    Card(suit, it)
                }
        }

    fun create() = Deck(cards.shuffled())
}
