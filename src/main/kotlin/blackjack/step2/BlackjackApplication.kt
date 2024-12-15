package blackjack.step2

import blackjack.step2.domain.BetManager
import blackjack.step2.domain.GameInitializer
import blackjack.step2.domain.GameManager
import blackjack.step2.domain.ProfitManager
import blackjack.step2.domain.RandomCardPicker
import blackjack.step2.view.ConsoleGameInteractor
import blackjack.step2.view.ConsoleInputHandler
import blackjack.step2.view.ConsoleOutputHandler

fun main() {
    val cardPicker = RandomCardPicker()
    val gameInitializer = GameInitializer(cardPicker)
    val players = ConsoleInputHandler.inputPlayerNames()
    val gameInteractor = ConsoleGameInteractor()
    val playerBets = ConsoleInputHandler.inputBetAmounts(players)
    val betManager = BetManager(playerBets)

    val initializedPlayers = gameInitializer.initializePlayers(players)
    val initializedDealer = gameInitializer.initializeDealer()
    ConsoleOutputHandler.printInitialCards(initializedDealer, initializedPlayers)

    val gameManager = GameManager(cardPicker, gameInteractor)
    val finalPlayers = initializedPlayers.map { gameManager.playTurn(it) }
    val finalDealer = gameManager.playTurn(initializedDealer)
    ConsoleOutputHandler.printFinalCards(listOf(finalDealer).plus(finalPlayers))

    val gameResults = ProfitManager.calculateFinalProfit(finalPlayers + finalDealer, betManager)
    ConsoleOutputHandler.printResults(gameResults)
}
