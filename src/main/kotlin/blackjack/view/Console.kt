package blackjack.view

import blackjack.model.card.Cards
import blackjack.model.playable.impl.Dealer
import blackjack.model.playable.impl.Player

object Console {

    fun Cards.presentPlayers(): String {
        return cards.joinToString(separator = ", ") { "${it.cardRank.alias}${it.suit.alias}" }
    }

    fun Player.presentPlayers(): String {
        return "${this.name}카드 : ${this.cards.presentPlayers()}"
    }

    fun Dealer.presentDealers(): String {
        return "딜러 카드 : ${this.cards.presentPlayers()}"
    }
}
