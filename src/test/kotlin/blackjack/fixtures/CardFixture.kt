package blackjack.fixtures

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.domain.Suit.SPADE

fun createCard(
    rank: String,
    suit: Suit = SPADE,
): Card {
    return Card(Rank(rank), suit)
}
