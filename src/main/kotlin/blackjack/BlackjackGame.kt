package blackjack

import blackjack.domain.BlackjackResults
import blackjack.domain.Dealer
import blackjack.domain.DealingShoe
import blackjack.domain.Gambler
import blackjack.domain.Participants
import blackjack.ui.BlackJackPrinter
import blackjack.ui.BlackJackReader

fun main() {
    val gamblers = BlackJackReader.readGamblers()
    val participants = Participants.of(Dealer(), gamblers)

    val dealingShoe = DealingShoe()
    dealingShoe.dealTwoCardsEach(participants)

    BlackJackPrinter.announceCardDistribution(participants)
    BlackJackPrinter.printCardMessage(participants)

    participants.extractGamblers()
        .forEach { gambler -> dealCardToGambler(gambler, dealingShoe) }

    dealCardToDealer(participants, dealingShoe)
    BlackJackPrinter.printAllFinalScore(participants)

    val blackjackResults = BlackjackResults(participants)
    BlackJackPrinter.printWinOrDefeatResults(blackjackResults)
}

private fun dealCardToGambler(gambler: Gambler, dealingShoe: DealingShoe) {
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

private fun dealCardToDealer(participants: Participants, dealingShoe: DealingShoe) {
    val dealer = participants.extractDealer()
    if (dealer.canNotReceiveCard()) {
        BlackJackPrinter.announceCanNotReceiveCard(dealer)
    } else {
        dealingShoe.dealCard(dealer)
        BlackJackPrinter.announceReceiveCard()
    }
}

