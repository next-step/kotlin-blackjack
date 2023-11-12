package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.View

class BlackJack {

    fun run() {
        val players = View.inputPlayerNames()
        val dealer = Dealer()

        players.initialCardDealing(dealer)
        View.initialCardDealingComment(players)
        View.showCards(players)

        while (players.noMoreHit().not()) {
            players.hitablePlayers()
                .processGame(dealer)
        }

        View.showResults(players)
    }

    private fun List<Player>.processGame(dealer: Dealer) {
        forEach {
            if (View.hitOrStand(it).not()) {
                it.noMoreHit()
                return@forEach
            }
            it.addCard(dealer.dealingOneCard())
            View.showCard(it)
        }
    }
}
