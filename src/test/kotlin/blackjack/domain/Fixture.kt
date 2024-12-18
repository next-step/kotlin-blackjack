package blackjack.domain

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.Suit

val CLUBS_ACE = PlayingCard.of(Suit.CLUBS, Denomination.ACE)
val CLUBS_TWO = PlayingCard.of(Suit.CLUBS, Denomination.TWO)
val CLUBS_TEN = PlayingCard.of(
    Suit.CLUBS,
    Denomination.TEN
)
val CLUBS_KING = PlayingCard.of(Suit.CLUBS, Denomination.KING)
