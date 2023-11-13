package blackjack.model

import blackjack.model.Point.Companion.WINNING_POINT

class Participants(
    val players: Players,
    val dealer: Dealer
) {

    fun initialCardDealing() {
        players.initialCardDealing(dealer)
        dealer.initialCardDealing()
    }

    fun makeResult() {
        val dealerPoint = dealer.getPoints()
        var dealerWinning = 0
        var dealerLosing = 0

        players.players.forEach {
            val playerPoint = it.getPoints()
            if (dealerPoint > WINNING_POINT) {
                if (playerPoint <= WINNING_POINT) {
                    dealerLosing++
                    it.makeWinner()
                } else {
                    it.makeLoser()
                }
            } else {
                if (playerPoint in (dealerPoint + 1)..WINNING_POINT) {
                    dealerLosing++
                    it.makeWinner()
                } else if (playerPoint < dealerPoint) {
                    dealerWinning++
                    it.makeLoser()
                } else {
                    it.makeLoser()
                }
            }
        }

        dealer.makeResult(dealerWinning, dealerLosing)
    }
}
