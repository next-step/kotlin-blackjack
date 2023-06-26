package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.enums.Condition
import blackjack.service.BlackjackService
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackController(
    private val blackjackService: BlackjackService,
    private val inputView: InputView,
    private val resultView: ResultView
) {

    fun play() {

        val blackjackGame = initBlackjackGame()

        raceBlackjack(blackjackGame)

        resultView.printEnter()
        resultView.printResultGame(blackjackGame.players)
    }

    private fun raceBlackjack(blackjackGame: BlackjackGame) {
        blackjackGame.players.forEach { player ->
            val answer = inputView.askForCardChoice(player)
            do {
                blackjackService.raceBlackjack(player, blackjackGame, answer)
                resultView.printPlayerAndCards(player)
            } while (player.currentCondition() == Condition.PLAY)
        }
    }

    private fun initBlackjackGame(): BlackjackGame {
        val inputPlayers = inputView.inputPlayers()
        resultView.printEnter()

        val players = replaceWhiteSpaceAndSplitByComma(inputPlayers)
        val blackjackGame = blackjackService.initBlackjackGame(players)

        resultView.printPlayers(players)
        resultView.printPlayersAndCards(blackjackGame.players)
        resultView.printEnter()

        return blackjackGame
    }

    private fun replaceWhiteSpaceAndSplitByComma(target: String): List<String> {
        return target.trim().replace("\\s".toRegex(), "").split(",")
    }
}
