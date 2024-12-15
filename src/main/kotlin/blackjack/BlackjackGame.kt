package blackjack

import blackjack.domain.DealingShoe
import blackjack.domain.Gamblers
import blackjack.ui.BlackJackPrinter
import blackjack.ui.BlackJackReader

fun main() {
    val gamblerNames = BlackJackReader.readGamblerNames()
    val gamblers = Gamblers.from(gamblerNames)

    val dealingShoe = DealingShoe()
    dealingShoe.dealTwoCardsEach(gamblers)
    BlackJackPrinter.announceCardDistribution(gamblers)
    BlackJackPrinter.printAllGamblersCardMessage(gamblers)

    gamblers.forEach { gambler ->
        while (true) {
            if (gambler.canNotReceiveCard()) {
                BlackJackPrinter.announceCanNotReceiveCard(gambler)
                break
            }

            val wantsMoreCard = BlackJackReader.readDecisionForMoreCard(gambler)
            if (wantsMoreCard.not()) {
                break
            }

            dealingShoe.dealCard(gambler)
            BlackJackPrinter.printGamblerCardMessage(gambler)
        }

        BlackJackPrinter.printLineFeed()
    }

    BlackJackPrinter.printResult(gamblers)
}
