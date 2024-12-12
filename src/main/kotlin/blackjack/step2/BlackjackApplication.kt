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
    val playerNames = ConsoleInputHandler.inputPlayerNames()
    val interactor = ConsoleGameInteractor()

    val dealer = gameInitializer.initializeDealer()
    val players = gameInitializer.initializePlayers(playerNames)
    ConsoleOutputHandler.printInitialCards(dealer, players)

    val gameManager = GameManager(cardPicker = cardPicker, interactor = interactor)
    val finalPlayers = players.map { gameManager.playTurn(it) }
    val finalDealer = gameManager.playTurn(dealer)

    ConsoleOutputHandler.printCards(finalDealer)
    finalPlayers.forEach { ConsoleOutputHandler.printCards(it) }
    val results = ResultManager.calculate(finalPlayers.plus(finalDealer))
    ConsoleOutputHandler.printResults(results)
}
