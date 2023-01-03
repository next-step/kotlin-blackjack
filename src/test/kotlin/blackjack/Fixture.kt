package blackjack

import blackjack.domain.bet.Bet
import blackjack.domain.card.Denomination
import blackjack.domain.card.PlayingCard
import blackjack.domain.card.PlayingCards
import blackjack.domain.card.Suit
import blackjack.domain.participant.state.role.Player

fun Player(name: String, cards: PlayingCards): Player = Player(name, cards, Bet(0))

fun Player(name: String, cards: PlayingCards, bet: Int): Player = Player(name, cards, Bet(bet))

fun SpadeCard(denomination: Denomination): PlayingCard = PlayingCard(Suit.SPADES, denomination)

fun ClubCard(denomination: Denomination): PlayingCard = PlayingCard(Suit.CLUBS, denomination)

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

val ClubAce = ClubCard(Denomination.ACE)
val ClubTwo = ClubCard(Denomination.TWO)
val ClubThree = ClubCard(Denomination.THREE)
val ClubFour = ClubCard(Denomination.FOUR)
val ClubFive = ClubCard(Denomination.FIVE)
val ClubSix = ClubCard(Denomination.SIX)
val ClubSeven = ClubCard(Denomination.SEVEN)
val ClubEight = ClubCard(Denomination.EIGHT)
val ClubNine = ClubCard(Denomination.NINE)
val ClubTen = ClubCard(Denomination.TEN)
val ClubJack = ClubCard(Denomination.JACK)
val ClubQueen = ClubCard(Denomination.QUEEN)
val ClubKing = ClubCard(Denomination.KING)
