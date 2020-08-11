package blackjack.model

object Winner {

    fun getTotalScore(dealer: Gamer, players: List<Gamer>): List<Score> {
        val playersWinOrNot = players
            .map { it.totalPoints }
            .map {
                if (it > Gamer.MAX_POINT) return@map false
                if (dealer.totalPoints > Gamer.MAX_POINT) return@map true
                return@map dealer.totalPoints < it
            }
        val dealerScore = Score(win = playersWinOrNot.count { !it }, lose = playersWinOrNot.count { it })
        val playersScore = playersWinOrNot.map {
            if (it) Score(win = 1, lose = 0) else Score(win = 0, lose = 1)
        }
        return listOf(listOf(dealerScore), playersScore).flatten()
    }
}
