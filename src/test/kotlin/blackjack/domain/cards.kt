package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Hand
import blackjack.domain.card.Suit

infix fun Denomination.to(suit: Suit) = Card(this, suit)
fun hand(vararg cards: Card): Hand = Hand(cards.toList())
fun cards(vararg cards: Card): List<Card> = cards.toList()
