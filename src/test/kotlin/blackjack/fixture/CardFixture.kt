package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit

internal fun cardFixture(
    suit: Suit = Suit.SPADE,
    rank: Rank = Rank.ACE,
): Card = Card(suit, rank)
