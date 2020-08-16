package blackjack.model

object Revenue {

    private const val BLACK_JACK_REVENUE = 1.5

    fun get(game: BlackJackGame, playersBettingMoney: List<Int>): List<Int> {
        val playersScore = mapToPlayersScore(game = game, bettingMoney = playersBettingMoney)
        val playersRevenue = getPlayersRevenue(playersScore)
        val dealerRevenue = getDealerRevenue(playersRevenue)
        return listOf(listOf(dealerRevenue), playersRevenue).flatten()
    }

    fun getPlayersRevenue(playersScore: List<Score>): List<Int> =
        playersScore.map { score ->
            when {
                score.isDraw && score.isBlackJack -> 0
                score.isBlackJack -> (score.bettingMoney * BLACK_JACK_REVENUE).toInt()
                score.isWin -> score.bettingMoney
                score.isDraw -> 0
                else -> -score.bettingMoney
            }
        }

    fun getDealerRevenue(playersRevenue: List<Int>) =
        -playersRevenue.reduce { acc, money -> acc + money }

    private fun mapToPlayersScore(game: BlackJackGame, bettingMoney: List<Int>): List<Score> {
        val dealerPoint = game.dealer.calculatePoint()
        return game.players.mapIndexed { index, player ->
            val (playerPoint, isBlackJack) = player.calculatePoint() to player.isBlackJack()
            when {
                playerPoint > Point.MAX_POINT -> Score(bettingMoney = bettingMoney[index])
                dealerPoint > Point.MAX_POINT ->
                    Score(isWin = true, isBlackJack = isBlackJack, bettingMoney = bettingMoney[index])
                dealerPoint == Point.MAX_POINT && playerPoint == Point.MAX_POINT ->
                    Score(isDraw = true, isBlackJack = isBlackJack, bettingMoney = bettingMoney[index])
                else -> Score(
                    isWin = dealerPoint < playerPoint,
                    isBlackJack = isBlackJack,
                    bettingMoney = bettingMoney[index]
                )
            }
        }
    }
}
