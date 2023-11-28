package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.Players

data class GameTable(
    val dealer: Dealer,
    val players: Players,
) {
    val isLastPlayerTurn: Boolean
        get() = players.isLastTurn

    val playerInTurn: Player
        get() = players.inTurn

    val playerInTurnAction: Action
        get() = playerInTurn.hitOrStand()

    val dealerAction: Action
        get() = dealer.hitOrStand()

    fun dealToAll(count: Int) {
        dealer.dealCards(count, *players.all.toTypedArray())
        dealer dealToSelf count
    }

    fun dealToPlayerInTurn(count: Int) {
        dealer.dealCards(count, players.inTurn)
    }

    fun dealToDealer(count: Int) {
        dealer dealToSelf count
    }

    fun passPlayerTurn() {
        players.changePlayer()
    }
}
