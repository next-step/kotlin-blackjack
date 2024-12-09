package blackjack.step2

import blackjack.step2.domain.GameManager
import blackjack.step2.domain.Player
import blackjack.step2.domain.RandomCardPicker
import blackjack.step2.domain.ResultManager
import blackjack.step2.view.InputView
import blackjack.step2.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerNames()
    val gameManager = GameManager(cardPicker = RandomCardPicker())
    val dealer = gameManager.pickFirstDealerCards()
    val players = gameManager.pickFirstPlayersCards(playerNames)

    val participants = listOf(dealer).plus(players)
    OutputView.printCardDealingIntro(playerNames)
    OutputView.printFirstDealtCard(participants)
    val updatedPlayers = InputView.inputMoreCard(participants = players, gameManager = gameManager)
    val updatedDealer = gameManager.pickDealerCardIfValid(dealer)

    OutputView.printCardResult(listOf(updatedDealer).plus(updatedPlayers))

    val gameResults = ResultManager.calculateResults(updatedDealer, updatedPlayers.map { it as Player })

    OutputView.printFinalResults(gameResults)
}
