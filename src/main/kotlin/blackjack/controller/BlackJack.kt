package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.View

class BlackJack {

    fun run() {
        val players = View.inputPlayerNames()
        val dealer = Dealer()
        val participants = Participants(players, dealer)

        participants.initialCardDealing()
        View.initialCardDealingComment(players)

        View.showCards(participants)

        participants.processGame()

        participants.makeResult()
        View.showResults(participants)
    }

    private fun Participants.processGame() {
        this.players.values.forEach {
            it.processGame(this.dealer)
        }
        View.dealerMoreCardComment(this.dealer.moreCard())
    }

    private fun Player.processGame(dealer: Dealer) {
        while (this.hit) {
            if (View.hitOrStand(this).not()) {
                this.noMoreHit()
                return
            }
            this.addCard(dealer.dealingOneCard())
            View.showCard(this)
        }
    }
}
