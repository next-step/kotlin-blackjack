package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object GameController {
    private const val DEFAULT_INITIAL_DRAW = 2

    fun run() {
        val deck = Deck()
        val players = prepareGame(deck)
        printBeforeStart(players)
        playGame(players, deck)
        endGame(players)
    }

    private fun prepareGame(deck: Deck): Participants {
        val players = InputView.getPlayerNames().map { Player(it) }
        val participants = Participants(players)

        repeat(DEFAULT_INITIAL_DRAW) {
            participants.players.forEach { it.drawCard(deck) }
        }
        return participants
    }

    private fun printBeforeStart(participants: Participants) {
        OutputView.printGameStart(participants.players.map { it.name }, DEFAULT_INITIAL_DRAW)
        OutputView.printPlayerCard(participants)
    }

    private fun playGame(participants: Participants, deck: Deck) {
        participants.players.forEach { playTurn(it, deck) }
    }

    private fun playTurn(player: Player, deck: Deck) {
        while (player.canDraw() && InputView.askDrawCard(player)) {
            player.drawCard(deck)
            OutputView.printPlayerCard(player)
        }
    }

    private fun endGame(participants: Participants) {
        OutputView.printPlayerResult(participants)
    }
}
