package blackjack.domain

class BlackJackGame(
    val players: List<BlackJackNormalPlayer>,
    val dealer: BlackJackDealer,
) {
    fun getGameResult(): BlackJackGameResult {
        val playerResults =
            players.map {
                BlackJackPlayerResult(
                    it.name,
                    BlackJackPlayResult.getResult(it.getBestSum(), dealer.getBestSum()),
                )
            }
        val winCount = playerResults.filter { it.result == BlackJackPlayResult.WIN }.size
        val loseCount = playerResults.filter { it.result == BlackJackPlayResult.LOSE }.size
        return BlackJackGameResult(playerResults, BlackJackDealerResult(winCount, loseCount))
    }

    fun getPlayersBestSum(): List<Int> {
        return players.map { it.getBestSum() }
    }
}
