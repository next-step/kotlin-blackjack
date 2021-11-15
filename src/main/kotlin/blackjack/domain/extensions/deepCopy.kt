package blackjack.domain.extensions

import blackjack.domain.Card

fun List<Card>.deepCopy() = this.map { it.copy() }
