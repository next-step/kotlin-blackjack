package blackjack.utils

import blackjack.domain.Card
import blackjack.enums.Rank
import blackjack.enums.Symbol

object DeckGenerator {

    fun generateDeck(): MutableList<Card> {

        val deck = mutableListOf<Card>()

        Symbol.values().forEach { symbol ->
            Rank.values().forEach { rank ->
                deck.add(Card(rank, symbol))
            }
        }
        return deck
    }
}
