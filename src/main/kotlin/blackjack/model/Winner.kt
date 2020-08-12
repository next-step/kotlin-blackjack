package blackjack.model

object Winner {

    fun getTotalScore(game: BlackJackGame): List<Score> {
        val playersWinOrNot = game.players
            .map { it.calculatePoint() }
            .map {
                if (it > Gamer.MAX_POINT) return@map false
                if (game.dealer.calculatePoint() > Gamer.MAX_POINT) return@map true
                return@map game.dealer.calculatePoint() < it
            }
        val dealerScore = Score(win = playersWinOrNot.count { !it }, lose = playersWinOrNot.count { it })
        val playersScore = playersWinOrNot.map {
            if (it) Score(win = 1) else Score()
        }
        return listOf(listOf(dealerScore), playersScore).flatten()
    }
}
