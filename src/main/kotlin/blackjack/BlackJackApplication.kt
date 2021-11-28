package blackjack

import blackjack.domain.BlackJackMachine
import blackjack.domain.player.Player
import blackjack.ui.BlackJackInputView
import blackjack.ui.BlackJackOutputView

fun main() = BlackJackApplication(BlackJackMachine(), BlackJackInputView, BlackJackOutputView).run()

class BlackJackApplication(
    private val machine: BlackJackMachine,
    private val inputView: BlackJackInputView,
    private val outputView: BlackJackOutputView
) {

    fun run() {
        var players = getPlayer()

        players = initPlayersCards(players)
        outputView.showPlayersCards(players)

        while (finish(players)) {
            players = players
                .map { play(it) }
                .run { toList() }
            outputView.showPlayersCards(players)
        }

        outputView.showGameResult(players)
    }

    private fun getPlayer(): List<Player> {
        return inputView.inputParticipantsPrompt()
            .map { Player.from(it) }
            .run { toList() }
    }

    private fun initPlayersCards(players: List<Player>): List<Player> {
        inputView.firstDrawMessage(players)
        return players.map { machine.init(it) }
            .run { toList() }
    }

    private fun finish(players: List<Player>): Boolean {
        return players.any { !it.finished }
    }

    private fun play(player: Player) =
        if (!player.finished) machine.play(inputView.askIntentionToPlay(player), player) else player
}
