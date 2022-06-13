package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Hand
import blackjack.domain.Player

fun createDealer(vararg cards: Card): Dealer = Dealer(Deck(cards.toList()))

fun createPlayer(name: String, vararg cards: Card): Player = Player(name, Hand(cards.toList()))
