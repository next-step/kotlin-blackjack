package blackjack.domain

fun cards(vararg card: Pair<Denomination, Suit>): Cards = Cards(card.toList().map { Card.from(it.first, it.second) })
