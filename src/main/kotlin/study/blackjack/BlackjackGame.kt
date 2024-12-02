package study.blackjack

import study.blackjack.model.BlackjackPlayer
import study.blackjack.view.InputView
import study.blackjack.view.ResultView

/**
 * @author 이상준
 */
class BlackjackGame(
    private var players: List<BlackjackPlayer> = mutableListOf(),
    private val blackjackGameService: BlackjackGameService = BlackjackGameService()
) {
    private val inputView = InputView()
    private val resultView = ResultView()

    fun run() {
        players = inputView.inputPlayerNames()
        resultView.printInitGiveCardsMessage(players, INIT_CARD_COUNT)
        players.forEach { player ->
            blackjackGameService.addCard(player)
            blackjackGameService.addCard(player)

            resultView.printInitCardOfPlayer(player)
        }

        players.forEach { player ->
            playGame(player)
        }

        finishGame()
    }

    private fun playGame(player: BlackjackPlayer) {
        while (inputView.inputGiveCardMessage(player)) {
            blackjackGameService.addCard(player)
            resultView.printInitCardOfPlayer(player)
        }

    }

    private fun finishGame() {
        players.forEach { player ->
            resultView.printResultCardOfPlayer(player)
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}


fun main() {
    BlackjackGame().run()
}
