package blackjack.fixtures

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.card.Suit.SPADE

fun createCard(
    rank: String,
    suit: Suit = SPADE,
): Card {
    return Card(Rank.from(rank), suit)
}
