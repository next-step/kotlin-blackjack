package blackjack

object Revenue {

    private const val BLACK_JACK_REVENUE = 1.5

    fun get(game: BlackJackGame, playersBettingMoney: List<Int>): List<Int> {
        val (dealerPoint, playersPoint) = game.getDealerPoint() to game.getPlayersPoint()
        val playersIsBlackJackState = game.getPlayersBlackJackState()
        val playersWinOrNot = getPlayersWinOrNot(playersPoint, playersIsBlackJackState, dealerPoint)
        val playersRevenue = getPlayersRevenue(playersWinOrNot, playersBettingMoney)
        val dealerRevenue = -playersRevenue.reduce { acc, money -> acc + money }
        return listOf(listOf(dealerRevenue), playersRevenue).flatten()
    }

    private fun getPlayersRevenue(playersWinOrNot: List<Score>, playersBettingMoney: List<Int>): List<Int> =
        playersWinOrNot
            .zip(playersBettingMoney)
            .map(::mapPlayerRevenue)

    private fun mapPlayerRevenue(scoreToMoney: Pair<Score, Int>): Int {
        val (score, money) = scoreToMoney.first to scoreToMoney.second
        return when {
            score.isBlackJack -> (money * BLACK_JACK_REVENUE).toInt()
            score.isWin -> money
            score.isDraw -> 0
            else -> -money
        }
    }

    private fun getPlayersWinOrNot(
        playersPoint: List<Point>,
        playersIsBlackJackState: List<Boolean>,
        dealerPoint: Point
    ): List<Score> =
        playersPoint.zip(playersIsBlackJackState).map {
            val (playerPoint, isBlackJack) = it.first to it.second
            when {
                dealerPoint > Point.MAX_POINT -> Score(isWin = true, isBlackJack = isBlackJack)
                playerPoint > Point.MAX_POINT -> Score()
                dealerPoint == Point.MAX_POINT && playerPoint == Point.MAX_POINT ->
                    Score(isDraw = true, isBlackJack = isBlackJack)
                else -> Score(isWin = dealerPoint < playerPoint, isBlackJack = isBlackJack)
            }
        }
}
