package view

import domain.BlackJackGame
import domain.BlackJackGame.endCheck
import domain.BlackJackGame.setInitialCards
import domain.Dealer

fun main() {
    val participants = InputView.getUserName()
    InputView.displayCardDivide(participants.joinToString { it.name }, "2")

    val dealer = Dealer("딜러")
    val allPlayer = listOf(dealer) + participants
    setInitialCards(allPlayer)

    allPlayer.forEach {
        InputView.displayHaveCard(it)
    }

    do {
        var noCnt = 0
        var continueGame = false

        participants.forEach {
            val enableToIssue = InputView.isYesOrNo(it.name)
            if (!enableToIssue) noCnt++

            BlackJackGame.drawCard(it)
            val isBust = BlackJackGame.isBust(it, enableToIssue)

            InputView.displayHaveCard(it)

            continueGame = endCheck(noCnt, participants.size, isBust)
        }
    } while (continueGame)

    val isMoreCardForDealer = dealer.isBelowForDealerScore(dealer.getSumOfCards())
    if (isMoreCardForDealer) {
        InputView.displayDealerCard()
        BlackJackGame.drawCard(dealer)
    }

    BlackJackGame.computeWinner(allPlayer)

    ResultView.displayPlayersScore(allPlayer)
    ResultView.displayWinOrLose(allPlayer)
}
