package blackjack.view

import blackjack.model.trump.Cards

object ViewUtil {
    fun toString(cards: Cards): String =
        cards.joinToString(separator = ", ") { "${it.cardNumber}${it.suit}" }
}
