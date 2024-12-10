package blackjack.step2

import blackjack.step2.domain.GameInitializer
import blackjack.step2.domain.GameManager
import blackjack.step2.domain.RandomCardPicker
import blackjack.step2.domain.ResultManager
import blackjack.step2.view.InputView
import blackjack.step2.view.OutputView

fun main() {
    val cardPicker = RandomCardPicker()
    val gameInitializer = GameInitializer(cardPicker)
    val playerNames = InputView.inputPlayerNames()

    val dealer = gameInitializer.initializeDealer()
    val players = gameInitializer.initializePlayers(playerNames)
    OutputView.printInitialCards(dealer, players)

    val gameManager = GameManager(cardPicker = cardPicker)
    val finalPlayers = players.map { gameManager.playTurn(it) }
    val finalDealer = gameManager.playTurn(dealer)

    OutputView.printCards(finalDealer)
    finalPlayers.forEach { OutputView.printCards(it) }
    val results = ResultManager.calculate(finalPlayers.plus(finalDealer))
    OutputView.printResults(results)
}
