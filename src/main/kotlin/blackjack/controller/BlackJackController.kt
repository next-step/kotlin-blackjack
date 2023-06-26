package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.enums.Condition
import blackjack.domain.enums.RaceFlag
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
        raceDealer(blackjackGame.dealer)
        resultView.printEnter()

        resultView.printResultScore(blackjackGame.players, blackjackGame.dealer)
        resultView.printEnter()

        val result = blackjackService.resultBlackjackGame(blackjackGame.players, blackjackGame.dealer)
        resultView.printResultGame(result)
    }

    private fun raceDealer(dealer: Dealer) {
        do {
            if (dealer.currentCondition() == Condition.PLAY) {
                blackjackService.raceDealer(dealer)
            }
            resultView.printDealerCard(dealer)
        } while (dealer.currentCondition() == Condition.PLAY)
    }

    private fun raceBlackjack(blackjackGame: BlackjackGame) {
        blackjackGame.players.forEach { player ->
            val answer = inputView.askForCardChoice(player)
            validAnswer(answer)
            do {
                blackjackService.raceBlackjack(player, blackjackGame, answer)
                resultView.printPlayerAndCards(player)
            } while (player.currentCondition() == Condition.PLAY)
        }
    }

    private fun validAnswer(answer: String) {
        require(RaceFlag.values().map { it.lowercaseName }.contains(answer)) {
            "응답 입력 값은 ${RaceFlag.Y.lowercaseName}, ${RaceFlag.N.lowercaseName} 중 입력해주세요."
        }
    }

    private fun initBlackjackGame(): BlackjackGame {
        val inputPlayers = inputView.inputPlayers()
        resultView.printEnter()

        val players = replaceWhiteSpaceAndSplitByComma(inputPlayers)
        val blackjackGame = blackjackService.initBlackjackGame(players)

        resultView.printPlayers(blackjackGame.players, blackjackGame.dealer)
        resultView.printPlayersAndCards(blackjackGame.players, blackjackGame.dealer)
        resultView.printEnter()

        return blackjackGame
    }

    private fun replaceWhiteSpaceAndSplitByComma(target: String): List<String> {
        return target.trim().replace("\\s".toRegex(), "").split(",")
    }
}
