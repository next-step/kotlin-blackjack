package blackjack.step2

import blackjack.step2.domain.GameInitializer
import blackjack.step2.domain.GameManager
import blackjack.step2.domain.RandomCardPicker
import blackjack.step2.domain.ResultManager
import blackjack.step2.view.ConsoleGameInteractor
import blackjack.step2.view.ConsoleInputHandler
import blackjack.step2.view.ConsoleOutputHandler

fun main() {
    val cardPicker = RandomCardPicker()
    val gameInitializer = GameInitializer(cardPicker)
    val inputPlayers = ConsoleInputHandler.inputPlayerNamesAndBets()
    val interactor = ConsoleGameInteractor()

    val initializedPlayers = gameInitializer.initializePlayers(inputPlayers)
    val initializedDealer = gameInitializer.initializeDealer()
    ConsoleOutputHandler.printInitialCards(initializedDealer, initializedPlayers)

    val gameManager = GameManager(cardPicker, interactor)
    val finalPlayers = initializedPlayers.map { gameManager.playTurn(it) }
    val finalDealer = gameManager.playTurn(initializedDealer)
    ConsoleOutputHandler.printFinalCards(listOf(finalDealer).plus(finalPlayers))

    val results = ResultManager.calculateFinalProfit(finalPlayers + finalDealer)
    ConsoleOutputHandler.printResults(results)
}
