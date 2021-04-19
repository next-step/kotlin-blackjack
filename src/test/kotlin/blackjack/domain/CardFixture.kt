package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardShape
import blackjack.domain.card.CardType
import blackjack.domain.card.Cards

val cardTwo = Card(CardShape.SPADE, CardType.TWO)
val cardThree = Card(CardShape.SPADE, CardType.THREE)
val cardQueen = Card(CardShape.SPADE, CardType.QUEEN)
val cardAce = Card(CardShape.SPADE, CardType.ACE)

fun cards(vararg cardTypes: CardType): Cards =
    Cards(cardTypes.map { Card(CardShape.SPADE, it) }.toList())
