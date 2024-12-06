package blackjack.controller

import blackjack.domain.Game
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {
    fun start() {
        val playerNames = InputView.inputPlayerNames().split(",").map { it.trim() }
        val game = Game(playerNames)

        ResultView.printSplitCardResult(game.players.map { it.name })

        game.players.forEach { ResultView.printPlayerCards(listOf(it.name to it.cards)) }

        game.players.forEach { player ->
            game.handlePlayerTurn(player) { playerName ->
                InputView.inputPickCard(playerName)
            }
            ResultView.printPlayerCards(listOf(player.name to player.cards))
        }

        ResultView.printFinalScores(game.players.map { it.name to it.score }, game.players)
    }
}
