package blackjack.controller

import blackjack.CardDrawCommand
import blackjack.GameCardStorage
import blackjack.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Blackjack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play(gameCardStorage: GameCardStorage) {
        val names = inputView.fetchPlayerNames()

        val players = names.map(::Player)

        players.forEach {
            it.take(gameCardStorage.drawCard())
            it.take(gameCardStorage.drawCard())
        }
        outputView.showInitialStatus(players)

        for (player in players) {
            runExtraCardDrawSession(player, gameCardStorage)
        }
    }

    private fun runExtraCardDrawSession(player: Player, gameCardStorage: GameCardStorage) {
        var isResultShown = false
        while (inputView.fetchCardDrawCommand(player) == CardDrawCommand.YES) {
            val nextCard = gameCardStorage.drawCard()
            player.take(nextCard)
            outputView.showCurrentStatusOf(player)
            isResultShown = true
        }
        if (isResultShown.not()) {
            outputView.showCurrentStatusOf(player)
        }
    }
}

