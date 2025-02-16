package blackjack

import blackjack.domain.BlackjackResults
import blackjack.domain.Dealer
import blackjack.domain.DealingShoe
import blackjack.domain.Gambler
import blackjack.domain.Participants
import blackjack.ui.BlackjackPrinter
import blackjack.ui.BlackjackReader

fun main() {
    val participants = participateInThisGame()

    val dealingShoe = DealingShoe()
    dealingShoe.dealTwoCardsEach(participants)

    BlackjackPrinter.announceCardDistribution(participants)
    BlackjackPrinter.printCardMessage(participants)

    val gamblers = participants.extractGamblers()
    placeBet(gamblers)
    dealCardToGamblers(dealingShoe, gamblers)

    val dealer = participants.extractDealer()
    dealCardToDealer(dealingShoe, dealer)
    BlackjackPrinter.printAllFinalScore(participants)

    val blackjackResults = dealer.determineResult(gamblers)
    BlackjackPrinter.printAllFinalProfit(blackjackResults)
}

private fun participateInThisGame(): Participants {
    val gamblers = BlackjackReader.readGamblerNames()
        .map { name -> Gambler(name) }
    return Participants.of(Dealer(), gamblers)
}

private fun placeBet(gamblers: List<Gambler>) {
    gamblers.forEach { gambler ->
        val betAmount = BlackjackReader.readBetAmount(gambler.name)
        gambler.placeBet(betAmount)
        BlackjackPrinter.printLineFeed()
    }
}

private fun dealCardToGamblers(
    dealingShoe: DealingShoe,
    gamblers: List<Gambler>
) {
    gamblers.forEach { gambler ->
        while (true) {
            if (gambler.canNotReceiveCard()) {
                BlackjackPrinter.announceCanNotReceiveCard(gambler)
                break
            }

            val wantsMoreCard = BlackjackReader.readDecisionForMoreCard(gambler.name)
            if (wantsMoreCard.not()) {
                break
            }

            dealingShoe.dealCard(gambler)
            BlackjackPrinter.printCardMessage(gambler)
        }

        BlackjackPrinter.printLineFeed()
    }
}

private fun dealCardToDealer(
    dealingShoe: DealingShoe,
    dealer: Dealer,
) {
    if (dealer.canNotReceiveCard()) {
        BlackjackPrinter.announceCanNotReceiveCard(dealer)
    } else {
        dealingShoe.dealCard(dealer)
        BlackjackPrinter.announceReceiveCard()
    }
}
