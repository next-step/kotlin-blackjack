package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

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
