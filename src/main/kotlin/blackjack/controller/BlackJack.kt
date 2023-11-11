package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.View

class BlackJack {

    fun run() {
        val players = View.inputPlayerNames()
        val dealer = Dealer()

        players.forEach {
            it.addCards(dealer.dealingTwoCards())
        }
        View.initialCardDealingComment(players)
        players.forEach { View.showCards(it) }

        while (!players.noMoreHit()) {
            players.processGame(dealer)
        }

        players.forEach { View.showResult(it) }
    }

    private fun List<Player>.noMoreHit() = !(any { it.hit })

    private fun List<Player>.processGame(dealer: Dealer) {
        filter { it.hit }
            .forEach {
                val hit = View.hitOrStand(it)
                if (!hit) {
                    it.noMoreHit()
                    return@forEach
                }
                it.addCard(dealer.dealingOneCard())
                View.showCards(it)
            }
    }
}
