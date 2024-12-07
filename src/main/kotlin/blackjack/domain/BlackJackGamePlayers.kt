package blackjack.domain

class BlackJackGamePlayers(
    val players: List<BlackJackNormalPlayer>,
    val dealer: BlackJackDealer,
) {
    fun getPlayerResults(): List<BlackJackPlayerResult> {
        return players.map {
            BlackJackPlayerResult(
                it.name,
                BlackJackPlayResult.getResult(it.getBestSum(), dealer.getBestSum()),
            )
        }
    }

    fun dealerDraw(blackJackDeck: BlackJackDeck): Boolean {
        if (dealer.drawPossible()) {
            dealer.drawCard(blackJackDeck)
            return true
        }
        return false
    }

    fun calculateProfit(playerResults: List<BlackJackPlayerResult>) {
        playerResults.forEach { result ->
            val normalPlayer = players.find { it.name.equals(result.name) } ?: throw IllegalArgumentException("이상해요")
            when (result.result) {
                BlackJackPlayResult.WIN -> {
                    normalPlayer.win()
                    dealer.lose(normalPlayer.bettingMoney, normalPlayer.isBlackJackPlayer())
                }
                BlackJackPlayResult.LOSE -> {
                    normalPlayer.lose()
                    dealer.win(normalPlayer.bettingMoney)
                }
                BlackJackPlayResult.DRAW -> {}
            }
        }
    }
}
