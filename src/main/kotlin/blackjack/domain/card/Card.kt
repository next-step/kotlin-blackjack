package blackjack.domain.card

import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit

data class Card(val denomination: Denomination, val suit: Suit)
