package blackjack.model

object Winner {

    fun getTotalScore(game: BlackJackGame): List<Score> {
        val playersWinOrNot = game.getPlayersPoint()
            .map { point -> setupWinner(playerPoint = point, dealerPoint = game.getDealerPoint()) }
        val (dealerScore, playersScore) = getScores(playersWinOrNot)

        return listOf(listOf(dealerScore), playersScore).flatten()
    }

    private fun setupWinner(playerPoint: Int, dealerPoint: Int): Boolean {
        if (playerPoint > Gamer.MAX_POINT) return false
        if (dealerPoint > Gamer.MAX_POINT) return true
        return dealerPoint < playerPoint
    }

    private fun getScores(playersWinOrNot: List<Boolean>): Pair<Score, List<Score>> {
        val dealerScore = Score(win = playersWinOrNot.count { !it }, lose = playersWinOrNot.count { it })
        val playersScore = playersWinOrNot.map { if (it) Score(win = 1) else Score() }
        return Pair(dealerScore, playersScore)
    }
}
