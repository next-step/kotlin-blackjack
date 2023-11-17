package blackjack.view

import blackjack.model.Cards
import blackjack.model.Player

object Console {

    fun Cards.present(): String {
        return cards.joinToString(separator = ", ") { "${it.cardRank.alias}${it.suit.alias}" }
    }

    fun Player.present(): String {
        return "${this.name}카드 : ${this.cards.present()}"
    }
}
