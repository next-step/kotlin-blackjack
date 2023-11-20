package blackjack.controller

import blackjack.dto.Money
import blackjack.model.Dealer
import blackjack.model.Dealer.Companion.DEALER_NAME
import blackjack.model.Participants
import blackjack.view.View

class BlackJack {

    fun run() {
        val players = View.inputPlayerData()
        val dealer = Dealer(DEALER_NAME, Money.ZERO)
        val participants = Participants(players, dealer)

        participants.initialCardDealing()
        View.initialCardDealingComment(players)

        View.showCards(participants)

        participants.processGame(
            View::dealerMoreCardComment,
            View::hitOrStand,
            View::showCard
        )

        participants.makeResult()
        View.showResults(participants)
    }
}
