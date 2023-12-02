package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.Players

data class GameTable(
    val players: Players,
    val dealer: Dealer = Dealer(),
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
        dealer.dealCards(count, *players.value.toTypedArray())
        dealer dealToSelf count
    }

    fun dealToPlayerInTurn(count: Int) {
        dealer.dealCards(count, players.inTurn)
    }

    fun dealToDealer(count: Int) {
        dealer dealToSelf count
    }

    fun passPlayerTurnIfNotLastTurn() {
        if (isLastPlayerTurn) return
        players.changePlayer()
    }
}
