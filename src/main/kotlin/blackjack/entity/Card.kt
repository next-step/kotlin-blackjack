package blackjack.entity

import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit

data class Card(val suit: Suit, val denomination: Denomination)
