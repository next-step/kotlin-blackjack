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
        val winCount = playerResults.filter { it.result == BlackJackPlayResult.LOSE }.count()
        val loseCount = playerResults.filter { it.result == BlackJackPlayResult.WIN }.count()
        return BlackJackGameResult(playerResults, BlackJackDealerResult(winCount, loseCount))
    }

    fun dealerDraw(blackJackDeck: BlackJackDeck): Boolean {
        if (dealer.drawPossible()) {
            dealer.drawCard(blackJackDeck)
            return true
        }
        return false
    }
}
