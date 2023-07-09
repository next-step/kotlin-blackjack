package blackjack

import blackjack.domain.Card
import blackjack.domain.Hands
import blackjack.domain.Symbol
import blackjack.domain.Type

val CLUBS_TWO = Card.of(Type.CLUBS, Symbol.TWO)
val SPADES_ACE = Card.of(Type.SPADES, Symbol.ACE)
val SPADES_FOUR = Card.of(Type.SPADES, Symbol.FOUR)
val HEARTS_KING = Card.of(Type.HEARTS, Symbol.KING)
val SPADES_KING = Card.of(Type.SPADES, Symbol.KING)

val BLACKJACK_HANDS = Hands(listOf(SPADES_ACE, HEARTS_KING))
