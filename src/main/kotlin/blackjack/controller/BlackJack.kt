package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.view.View

class BlackJack {

    fun run() {
        val players = View.inputPlayerNames().apply {
            bettingMoney(View::inputBettingMoney)
        }
        val dealer = Dealer()
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
