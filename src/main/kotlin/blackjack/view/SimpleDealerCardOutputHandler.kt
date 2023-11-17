package blackjack.view

import blackjack.business.participants.Dealer

object SimpleDealerCardOutputHandler {
    fun print(dealer: Dealer) {
        println("딜러: ${dealer.cards.joinToString(", ") { it.toString() }}")
    }
}
