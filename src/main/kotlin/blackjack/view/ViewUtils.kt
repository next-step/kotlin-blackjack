package blackjack.view

import blackjack.model.Cards

fun toString(cards: Cards): String =
    cards.joinToString(separator = ", ") { "${it.cardNumber}${it.suit}" }
