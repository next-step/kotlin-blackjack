package blakjack.domain.extension

import blakjack.domain.Card
import blakjack.domain.Cards

fun cards(vararg cards: Card): Cards = Cards(cards.toSet())
