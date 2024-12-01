package blackjack.step2

import blackjack.step2.domain.Dealer
import blackjack.step2.domain.GameManager
import blackjack.step2.domain.RandomCardPicker
import blackjack.step2.view.InputView
import blackjack.step2.view.OutputView

fun main() {
    val players = InputView.inputPlayerNames()
    val gameManager = GameManager(Dealer(RandomCardPicker()))
    val firstPlayerCards = gameManager.dealFirstCards(players)
    OutputView.printFirstDealtCard(firstPlayerCards)
    val updatedPlayerCards = InputView.inputMoreCard(playerCards = firstPlayerCards, gameManager = gameManager)
    OutputView.printCardResult(updatedPlayerCards)
}
