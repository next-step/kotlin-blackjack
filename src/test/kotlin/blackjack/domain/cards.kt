package blackjack.domain

infix fun Denomination.to(suit: Suit) = Card(this, suit)
fun hand(vararg cards: Card): Hand = Hand(cards.toList())
