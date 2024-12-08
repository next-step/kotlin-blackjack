package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Gamblers
import blackjack.ui.BlackJackPrinter
import blackjack.ui.BlackJackReader

fun main() {
    val gamblerNames = BlackJackReader.readGamblerNames()
    val gamblers = Gamblers.from(gamblerNames)

    Dealer.dealTwoCardsEach(gamblers)
    BlackJackPrinter.announceCardDistribution(gamblers)
    BlackJackPrinter.printAllGamblersCardMessage(gamblers)

    gamblers.forEach { gambler ->
        while (true) {
            val wantsMoreCard = BlackJackReader.readDecisionForMoreCard(gambler)
            if (wantsMoreCard.not()) {
                break
            }

            if (gambler.canNotReceiveCard()) {
                BlackJackPrinter.announceCanNotReceiveCard(gambler)
                break
            }

            Dealer.dealCard(gambler)
            BlackJackPrinter.printGamblerCardMessage(gambler)
        }

        BlackJackPrinter.printLineFeed()
    }

    BlackJackPrinter.printResult(gamblers)
}