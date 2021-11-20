package blackjack.presentation

import blackjack.domain.deck.Deck
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players
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
                val inputCardSign = inputCardSign(player)

                if (player.isStand(inputCardSign)) {
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

    private fun inputCardSign(player: Player): String {
        if (player.isFinished()) {
            return FINISHED_SIGN
        }
        return InputView.inputCardSign(player)
    }
}
