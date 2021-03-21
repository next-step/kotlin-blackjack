package blackjack.domain.deck

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suit

object DeckFactory {
    private val cards: List<Card> = Suit.values()
        .flatMap { suit ->
            Denomination.values()
                .map {
                    Card(suit, it)
                }
        }

    fun create() = Deck(cards.shuffled().toSet())
}
