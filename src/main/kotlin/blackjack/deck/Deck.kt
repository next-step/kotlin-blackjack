package blackjack.deck

import blackjack.card.Card
import blackjack.card.Rank
import blackjack.card.Suit

class Deck(
    private var cards: Cards = Cards(cards = createFullDeck()),
) {
    fun isRemainCard(): Boolean = cards.isNotEmpty()

    fun draw(card: Card): Cards? =
        cards
            .draw(drawCard = card)
            ?.also { this.cards = it }
}

private fun createFullDeck(): List<Card> =
    Rank.entries.flatMap { rank ->
        Suit.entries.map { suit -> Card(rank = rank, suit = suit) }
    }
