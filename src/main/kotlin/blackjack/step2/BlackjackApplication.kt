package blackjack.step2

import blackjack.step2.domain.GameManager
import blackjack.step2.domain.RandomCardPicker
import blackjack.step2.view.InputView
import blackjack.step2.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerNames()
    val gameManager = GameManager(cardPicker = RandomCardPicker())
    val firstPlayerCards = gameManager.pickFirstCards(playerNames)
    OutputView.printFirstDealtCard(firstPlayerCards)
    val updatedPlayerCards = InputView.inputMoreCard(players = firstPlayerCards, gameManager = gameManager)
    OutputView.printCardResult(updatedPlayerCards)
}
