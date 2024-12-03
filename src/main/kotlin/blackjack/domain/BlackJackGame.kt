package blackjack.domain

class BlackJackGame(
    val players: List<BlackJackPlayer>,
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
}
