package blackjack.domain

fun cards(vararg card: Pair<Denomination, Suit>): List<Card> = card.toList().map { Card.from(it.first, it.second) }
fun hand(vararg card: Pair<Denomination, Suit>): Hand = Hand(cards(*card))
