package blackjack.controller

import blackjack.domain.Player
import blackjack.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {
    private val blackJackService = BlackJackService()

    fun start() {
        val playerNames = readPlayerNames()
        val players = blackJackService.createPlayers(playerNames)
        splitCards(playerNames, players)
        showPlayCards(players)

        players.forEach { player ->
            askPickCard(player)
        }

        showResult(players)
    }

    private fun readPlayerNames(): List<String> {
        val playersInput = InputView.inputPlayerNames()
        return blackJackService.splitPlayerNames(playersInput)
    }

    private fun splitCards(playerNames: List<String>, players: List<Player>) {
        blackJackService.distributeInitialCards(players)
        ResultView.printSplitCardResult(playerNames)
    }

    private fun showPlayCards(players: List<Player>) {
        val playerCardsInfo = players.map { it.name to it.getCardList() }
        ResultView.printPlayerCards(playerCardsInfo)
    }

    private fun askPickCard(player: Player) {
        while (InputView.inputPickCard(player.name) == "y") {
            player.addCards(blackJackService.card.drawCards(1))
            ResultView.printPlayerCards(listOf(player.name to player.getCardList()))
        }
    }

    private fun showResult(players: List<Player>) {
        val scores = blackJackService.calculateScores(players)
        ResultView.printFinalScores(scores, players)
    }
}
