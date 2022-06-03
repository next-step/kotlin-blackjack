package blackjack.domain

fun hand(vararg card: Pair<Denomination, Suit>): Hand = Hand(card.toList().map { Card.from(it.first, it.second) })
