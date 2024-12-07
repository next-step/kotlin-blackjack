package blackjack.domain

class BlackJackGame(
    val blackJackGamePlayers: BlackJackGamePlayers,
    val blackJackDeck: BlackJackDeck,
) {
    constructor(
        players: List<BlackJackNormalPlayer>,
        dealer: BlackJackDealer,
        blackJackDeck: BlackJackDeck,
    ) : this(BlackJackGamePlayers(players, dealer), blackJackDeck)

    fun calculateResult() {
        blackJackGamePlayers.calculateProfit(getPlayerResults())
    }

    fun getGameResult(): BlackJackGameResult {
        val playerResults = getPlayerResults()
        val winCount = playerResults.count { it.result == BlackJackPlayResult.LOSE }
        val loseCount = playerResults.count { it.result == BlackJackPlayResult.WIN }
        return BlackJackGameResult(playerResults, BlackJackDealerResult(winCount, loseCount))
    }

    private fun getPlayerResults(): List<BlackJackPlayerResult> {
        return blackJackGamePlayers.getPlayerResults()
    }

    fun dealerDraw(): Boolean {
        return blackJackGamePlayers.dealerDraw(blackJackDeck)
    }

    fun getNormalPlayers(): List<BlackJackNormalPlayer> {
        return blackJackGamePlayers.players
    }

    fun getDealer(): BlackJackDealer {
        return blackJackGamePlayers.dealer
    }
}
