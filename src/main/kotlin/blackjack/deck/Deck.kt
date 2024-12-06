package blackjack.deck

import blackjack.card.Card
import blackjack.card.Rank
import blackjack.card.Suit

class Deck(
    var cards: Cards = Cards(cards = createFullDeck()),
) {
    fun draw(): Card {
        require(cards.isNotEmpty()) { "카드 목록이 비어있습니다." }
        return cards
            .draw()
            .also { cards.discard(it) }
    }
}

private fun createFullDeck(): List<Card> =
    Rank.entries
        .flatMap { rank ->
            Suit.entries
                .map { suit -> Card(rank = rank, suit = suit) }
        }.shuffled()
