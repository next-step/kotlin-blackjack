package blackjack.controller

import blackjack.model.BlackjackState
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackjackController {
    fun run() {
        var blackjackState = BlackjackState(InputView.inputPlayers()).giveInitCards()
        OutputView.printInitialState(blackjackState.players)
        while (!blackjackState.isAllPlayersGameOver()) {
            val player = blackjackState.findNotOverPlayer()
            blackjackState = giveCardToPlayer(player, blackjackState)
            val newPlayer = blackjackState.findPlayer(player.name)
            OutputView.printPlayer(newPlayer)
        }
        OutputView.printPlayersWithScore(blackjackState.players)
    }

    private fun giveCardToPlayer(player: Player, blackjackState: BlackjackState): BlackjackState {
        if (InputView.inputConditionToGiveCard(player)) {
            return blackjackState.giveCard(player)
        } else {
            return blackjackState.setGameOver(player)
        }
    }
}
