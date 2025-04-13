package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit

interface DeckGenerator {
    fun generate(): List<Card>
}

object RandomDeckGenerator : DeckGenerator {
    override fun generate() =
        CardNumber.entries.flatMap { number ->
            Suit.entries.map { suit ->
                Card(number, suit)
            }
        }
}
