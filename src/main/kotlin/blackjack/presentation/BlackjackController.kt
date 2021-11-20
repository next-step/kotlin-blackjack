package blackjack.presentation

import blackjack.domain.deck.Deck
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players
import blackjack.domain.state.State.Companion.CAN_PLAY
import blackjack.domain.state.State.Companion.FINISHED_SIGN
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val deck: Deck = Deck.init(),
) {
    fun run() {
        val players = Players.from(InputView.inputPlayers())
        val preparedPlayers = prepare(players)
        val blackjackResult = play(preparedPlayers.value)
        OutputView.printBlackjackResult(blackjackResult.value)
    }

    private fun prepare(players: Players): Players {
        val preparedPlayers = players.prepare(deck)
        OutputView.printStartGame(players)
        return preparedPlayers
    }

    private fun play(players: List<Player>): Players {
        val completedBlackjackPlayers = mutableListOf<Player>()
        for (player in players) {
            while (true) {
                val playable = inputPlayable(player)

                if (player.isStand(playable)) {
                    player.stand()
                    OutputView.printPlayerCard(player)
                    completedBlackjackPlayers.add(player)
                    break
                }
                val progressedBlackjack = player.play(deck)
                println()
                OutputView.printPlayerCard(player)

                if (progressedBlackjack.isFinished()) {
                    completedBlackjackPlayers.add(progressedBlackjack)
                    break
                }
            }
        }
        return Players.from(completedBlackjackPlayers)
    }

    private fun inputPlayable(player: Player): Boolean {
        if (player.isFinished()) {
            return false
        }
        val playable = InputView.inputCardSign(player)
        if (playable == FINISHED_SIGN) {
            return false
        }
        return playable == CAN_PLAY
    }
}
