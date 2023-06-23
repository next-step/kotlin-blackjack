package blackjack.controller

import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {
    private const val DEFAULT_INITIAL_DRAW = 2
    private var toggle = false

    fun run() {
        val players = prepareGame()
        printBeforeStart(players)
        playGame(players)
        endGame(players)
    }

    private fun prepareGame(): Participants {
        val players = InputView.getPlayerNames().map { Player(it) }
        val participants = Participants(players)

        repeat(DEFAULT_INITIAL_DRAW) {
            participants.players.forEach { it.drawCard() }
        }
        return participants
    }

    private fun printBeforeStart(participants: Participants) {
        OutputView.printGameStart(participants.players.map { it.name }, DEFAULT_INITIAL_DRAW)
        OutputView.printPlayerCard(participants)
    }

    private fun playGame(participants: Participants) {
        while (!toggle) {
            participants.players.forEach { playTurn(it) }
        }
    }

    private fun playTurn(player: Player) {
        while (player.canDraw() && InputView.askDrawCard(player)) {
            player.drawCard()
            OutputView.printPlayerCard(player)
        }
        toggle = true
    }

    private fun endGame(participants: Participants) {
        OutputView.printPlayerResult(participants)
    }
}
