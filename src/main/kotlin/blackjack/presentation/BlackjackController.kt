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
        val table = settingTable(players)
        val blackjackResult = play(table)
        OutputView.printBlackjackResult(blackjackResult)
    }

    private fun settingTable(players: Players): List<Player> {
        val completedSettingTable = players.settingTable(deck)
        OutputView.printStartGame(players)
        return completedSettingTable
    }

    private fun play(players: List<Player>): List<Player> {
        val completedBlackjackPlayers = mutableListOf<Player>()
        for (player in players) {
            while (true) {
                val inputCardSign = inputCardSign(player)

                if (player.state.isStand(inputCardSign)) {
                    player.stand()
                    OutputView.printPlayerCard(player)
                    completedBlackjackPlayers.add(player)
                    break
                }
                val progressedBlackjack = player.play(deck)
                println()
                OutputView.printPlayerCard(player)

                if (progressedBlackjack.state.isFinished()) {
                    completedBlackjackPlayers.add(progressedBlackjack)
                    break
                }
            }
        }
        return completedBlackjackPlayers
    }

    private fun inputCardSign(player: Player): String {
        if (player.state.isFinished()) {
            return FINISHED_SIGN
        }
        return InputView.inputCardSign(player)
    }
}
