package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.enums.Condition
import blackjack.dto.PlayerInfo
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

        if (!blackjackGame.checkAllPlayersBlackjack()) {
            raceBlackjack(blackjackGame)
            resultView.printEnter()
            raceDealer(blackjackGame.dealer)
            resultView.printEnter()
        }

        resultView.printResultScore(blackjackGame.players, blackjackGame.dealer)
        resultView.printEnter()

        val result = blackjackGame.resultBlackjackGameMoney()
        resultView.printResultMoneyGame(result)
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
            while(player.currentCondition() == Condition.PLAY) {
                val answer = inputView.askForCardChoice(player)
                blackjackService.raceBlackjack(player, blackjackGame, answer)
                resultView.printPlayerAndCards(player)
            }
        }
    }

    private fun initBlackjackGame(): BlackjackGame {
        val inputPlayers = inputView.inputPlayers()
        resultView.printEnter()
        val players = replaceWhiteSpaceAndSplitByComma(inputPlayers)
        val playerInfo = playerInfos(players)

        val blackjackGame = blackjackService.initBlackjackGame(playerInfo)

        resultView.printPlayers(blackjackGame.players, blackjackGame.dealer)
        resultView.printPlayersAndCards(blackjackGame.players, blackjackGame.dealer)
        resultView.printEnter()

        return blackjackGame
    }

    private fun playerInfos(players: List<String>) = players.map { player ->
        val betAmount = inputView.inputBetAmount(player)
        resultView.printEnter()
        PlayerInfo(name = player, betAmount = betAmount.toDouble())
    }

    private fun replaceWhiteSpaceAndSplitByComma(target: String): List<String> {
        return target.trim().replace("\\s".toRegex(), "").split(",")
    }
}
