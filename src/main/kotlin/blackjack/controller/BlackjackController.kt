package blackjack.controller

import blackjack.model.BlackjackState
import blackjack.model.Player
import blackjack.view.BlackjackView

object BlackjackController {
    fun run() {
        var blackjackState = BlackjackState(BlackjackView.inputPlayers()).giveInitCards()
        BlackjackView.printInitialState(blackjackState.players)
        while (!blackjackState.isAllPlayersGameOver()) {
            val player = blackjackState.findNotOverPlayer()
            blackjackState = giveCardToPlayer(player, blackjackState)
            val newPlayer = blackjackState.findPlayer(player.name)
            BlackjackView.printPlayer(newPlayer)
        }
        BlackjackView.printPlayersWithScore(blackjackState.players)
    }

    private fun giveCardToPlayer(player: Player, blackjackState: BlackjackState): BlackjackState {
        if (BlackjackView.inputConditionToGiveCard(player)) {
            return blackjackState.giveCard(player)
        } else {
            return blackjackState.setGameOver(player)
        }
    }
}
