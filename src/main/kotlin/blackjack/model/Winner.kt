package blackjack.model

object Winner {

    fun getTotalScore(game: BlackJackGame): List<Score> {
        val playersWinOrNot = game.players
            .map { it.totalPoints }
            .map {
                if (it > Gamer.MAX_POINT) return@map false
                if (game.dealer.totalPoints > Gamer.MAX_POINT) return@map true
                return@map game.dealer.totalPoints < it
            }
        val dealerScore = Score(win = playersWinOrNot.count { !it }, lose = playersWinOrNot.count { it })
        val playersScore = playersWinOrNot.map {
            if (it) Score(win = 1, lose = 0) else Score(win = 0, lose = 1)
        }
        return listOf(listOf(dealerScore), playersScore).flatten()
    }
}
