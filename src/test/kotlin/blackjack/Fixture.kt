package blackjack

import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.Suit

fun SpadeCard(denomination: Denomination): PlayingCard = PlayingCard(Suit.SPADES, denomination)

val SpadeAce = SpadeCard(Denomination.ACE)
val SpadeTwo = SpadeCard(Denomination.TWO)
val SpadeThree = SpadeCard(Denomination.THREE)
val SpadeFour = SpadeCard(Denomination.FOUR)
val SpadeFive = SpadeCard(Denomination.FIVE)
val SpadeSix = SpadeCard(Denomination.SIX)
val SpadeSeven = SpadeCard(Denomination.SEVEN)
val SpadeEight = SpadeCard(Denomination.EIGHT)
val SpadeNine = SpadeCard(Denomination.NINE)
val SpadeTen = SpadeCard(Denomination.TEN)
val SpadeJack = SpadeCard(Denomination.JACK)
val SpadeQueen = SpadeCard(Denomination.QUEEN)
val SpadeKing = SpadeCard(Denomination.KING)
