package controller

import model.Person

class GameResultReader {
    fun decideResult(players: List<Person>) {
        val dealer = players[0]
        val dealerTotalNumber: Int = CardNumberCalculator().sumCardNumbers(dealer.openCard())
        var playerTotalNumber: Int
        var dealerWinCount: Int = 0
        var dealerLoseCount: Int = 0
        var dealerDrawCount: Int = 0

        for (i in 1 until players.size) {
            playerTotalNumber = CardNumberCalculator().sumCardNumbers(players[i].openCard())
            if (dealerTotalNumber > BLACK_JACK) {
                players[i].updateResult(WIN)
                dealerLoseCount++
                continue
            }
            if (playerTotalNumber > BLACK_JACK) {
                players[i].updateResult(LOSE)
                dealerWinCount++
                continue
            }
            if (dealerTotalNumber == BLACK_JACK && playerTotalNumber == BLACK_JACK) {
                players[i].updateResult(DRAW)
                dealerDrawCount++
                continue
            }
            if (dealerTotalNumber == BLACK_JACK) {
                players[i].updateResult(LOSE)
                dealerWinCount++
                continue
            }
            if ((BLACK_JACK - dealerTotalNumber) < (BLACK_JACK - playerTotalNumber)) {
                players[i].updateResult(LOSE)
                dealerWinCount++
                continue
            }
            if ((BLACK_JACK - dealerTotalNumber) > (BLACK_JACK - playerTotalNumber)) {
                players[i].updateResult(WIN)
                dealerLoseCount++
                continue
            }
            if ((BLACK_JACK - dealerTotalNumber) == (BLACK_JACK - playerTotalNumber)) {
                players[i].updateResult(DRAW)
                dealerDrawCount++
                continue
            }
        }
        dealer.updateResult("${dealerWinCount}승 ${dealerLoseCount}패 ${dealerDrawCount}무")
    }

    companion object {
        private const val WIN = "승"
        private const val LOSE = "패"
        private const val DRAW = "무"
        private const val BLACK_JACK = 21
    }
}
