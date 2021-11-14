package blackjack.presentation

import blackjack.application.BlackjackGame
import blackjack.domain.deck.Deck
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

    private fun settingTable(players: Players): List<BlackjackGame> {
        val list = mutableListOf<BlackjackGame>()
        for (player in players.value) {
            val blackjackGame = BlackjackGame.create(player)
            list.add(blackjackGame.completeDeal(deck))
        }
        OutputView.printStartGame(players)
        return list
    }

    private fun play(blackjackGames: List<BlackjackGame>): List<BlackjackGame> {
        val completedBlackjackGame = mutableListOf<BlackjackGame>()
        for (blackjackGame in blackjackGames) {
            while (true) {
                val inputCardSign = inputCardSign(blackjackGame)
                val progressedBlackjack = blackjackGame.play(deck, inputCardSign)
                OutputView.printPlayerCard(blackjackGame.player)

                if (progressedBlackjack.state.isFinished()) {
                    completedBlackjackGame.add(progressedBlackjack)
                    break
                }
            }
        }
        return completedBlackjackGame
    }

    private fun inputCardSign(blackjackGame: BlackjackGame): String {
        if (blackjackGame.state.isFinished()) {
            return FINISHED_SIGN
        }
        return InputView.inputCardSign(blackjackGame.player)
    }
}
