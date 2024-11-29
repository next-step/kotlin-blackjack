package blackjack.controller

import blackjack.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {
    private val blackJackService = BlackJackService()

    fun start() {
        val playersInput = InputView.inputPlayerNames()
        val playerNames = blackJackService.splitPlayerNames(playersInput)

        val players = blackJackService.createPlayers(playerNames)
        blackJackService.distributeInitialCards(players)

        ResultView.printSplitCardResult(playerNames)

        val playerCardsInfo = players.map { it.name to it.getCardList() }
        ResultView.printPlayerCards(playerCardsInfo)
    }
}
