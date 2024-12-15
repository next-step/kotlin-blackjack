package blackjack

import blackjack.domain.Dealer
import blackjack.domain.DealingShoe
import blackjack.domain.Participants
import blackjack.ui.BlackJackPrinter
import blackjack.ui.BlackJackReader

fun main() {
    val dealer = Dealer()
    val gamblers = BlackJackReader.readGamblers()
    val participants = Participants.of(dealer, gamblers)

    val dealingShoe = DealingShoe()
    dealingShoe.dealTwoCardsEach(participants)

    BlackJackPrinter.announceCardDistribution(participants)
    BlackJackPrinter.printCardMessage(participants)

    participants.extractGamblers()
        .forEach { gambler ->
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
                BlackJackPrinter.printCardMessage(gambler)
            }

            BlackJackPrinter.printLineFeed()
        }

    if (dealer.canNotReceiveCard()) {
        BlackJackPrinter.announceCanNotReceiveCard(dealer)
    } else {
        dealingShoe.dealCard(dealer)
        BlackJackPrinter.announceReceiveCard()
    }

    BlackJackPrinter.printResult(participants)
}
