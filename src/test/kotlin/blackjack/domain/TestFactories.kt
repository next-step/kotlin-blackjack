package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Hand
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit

fun card(rank: Rank, suit: Suit = Suit.CLUB): Card = Card(suit, rank)

fun hand(vararg cards: Card): Hand = Hand(cards.toMutableList())

fun deck(vararg cards: Card): Deck = Deck(cards.toMutableList().let(::ArrayDeque))

fun deck(cards: List<Card>): Deck = Deck(cards.let(::ArrayDeque))
