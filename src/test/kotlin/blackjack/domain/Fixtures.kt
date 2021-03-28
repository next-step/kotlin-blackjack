package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

val CLUB_ACE = Card.of(Suit.CLUBS, Denomination.ACE)
val CLUB_TWO = Card.of(Suit.CLUBS, Denomination.TWO)
val CLUB_TEN = Card.of(Suit.CLUBS, Denomination.TEN)
val CLUB_KING = Card.of(Suit.CLUBS, Denomination.KING)