package blackjack.deck

import blackjack.card.Card
import blackjack.card.Rank
import blackjack.card.Suit

class Deck(
    private var cards: Cards = Cards(cards = createFullDeck()),
) {
    fun draw(): Card =
        cards.draw().let { (drawnCard, remainCards) ->
            cards = Cards(cards = remainCards)
            drawnCard
        }
}

private fun createFullDeck(): List<Card> =
    Rank.entries
        .flatMap { rank ->
            Suit.entries
                .map { suit -> Card(rank = rank, suit = suit) }
        }.shuffled()
