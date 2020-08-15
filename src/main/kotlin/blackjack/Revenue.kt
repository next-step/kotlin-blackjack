package blackjack

object Revenue {

    private const val BLACK_JACK_REVENUE = 1.5

    fun getRevenue(game: BlackJackGame, playersBettingMoney: List<Int>): List<Int> {
        val (dealerPoint, playersPoint) = game.getDealerPoint() to game.getPlayersPoint()
        val playersIsBlackJackState = game.getPlayersBlackJackState()
        val playersWinOrNot = playersPoint.zip(playersIsBlackJackState).map {
            when {
                dealerPoint > Point.MAX_POINT -> Score(isWin = true, isBlackJack = it.second)
                it.first > Point.MAX_POINT -> Score()
                dealerPoint == Point.MAX_POINT && it.first == Point.MAX_POINT ->
                    Score(isDraw = true, isBlackJack = it.second)
                else -> Score(isWin = dealerPoint < it.first, isBlackJack = it.second)
            }
        }
        val playersRevenue = playersWinOrNot
            .zip(playersBettingMoney)
            .map {
                when {
                    it.first.isBlackJack -> (it.second * BLACK_JACK_REVENUE).toInt()
                    it.first.isWin -> it.second
                    it.first.isDraw -> 0
                    else -> -it.second
                }
            }
        val dealerRevenue = -playersRevenue.reduce { acc, money -> acc + money }
        return listOf(listOf(dealerRevenue), playersRevenue).flatten()
    }
}
