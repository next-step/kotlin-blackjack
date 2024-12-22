package blackjack

import blackjack.domain.BlackjackResults
import blackjack.domain.Dealer
import blackjack.domain.DealingShoe
import blackjack.domain.Gambler
import blackjack.domain.Participants
import blackjack.ui.BlackjackPrinter
import blackjack.ui.BlackjackReader

fun main() {
    val gamblers = BlackjackReader.readGamblers()
    val participants = Participants.of(Dealer(), gamblers)

    val dealingShoe = DealingShoe()
    dealingShoe.dealTwoCardsEach(participants)

    BlackjackPrinter.announceCardDistribution(participants)
    BlackjackPrinter.printCardMessage(participants)

    participants.extractGamblers()
        .forEach { gambler -> dealCardToGambler(gambler, dealingShoe) }

    dealCardToDealer(participants, dealingShoe)
    BlackjackPrinter.printAllFinalScore(participants)

    val blackjackResults = BlackjackResults(participants)
    BlackjackPrinter.printWinOrDefeatResults(blackjackResults)
}

private fun dealCardToGambler(
    gambler: Gambler,
    dealingShoe: DealingShoe,
) {
    while (true) {
        if (gambler.canNotReceiveCard()) {
            BlackjackPrinter.announceCanNotReceiveCard(gambler)
            break
        }

        val wantsMoreCard = BlackjackReader.readDecisionForMoreCard(gambler)
        if (wantsMoreCard.not()) {
            break
        }

        dealingShoe.dealCard(gambler)
        BlackjackPrinter.printCardMessage(gambler)
    }

    BlackjackPrinter.printLineFeed()
}

private fun dealCardToDealer(
    participants: Participants,
    dealingShoe: DealingShoe,
) {
    val dealer = participants.extractDealer()
    if (dealer.canNotReceiveCard()) {
        BlackjackPrinter.announceCanNotReceiveCard(dealer)
    } else {
        dealingShoe.dealCard(dealer)
        BlackjackPrinter.announceReceiveCard()
    }
}
