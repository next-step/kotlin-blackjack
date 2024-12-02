package blackjack.domain

class BlackJackGame(
    val players: List<BlackJackPlayer>,
    val dealer: BlackJackDealer?,
) {
    fun getWinPlayer(): List<BlackJackPlayer> {
        val max = players.maxOf { it.getBestSum() }
        return players.filter { it.getBestSum() == max }
    }
}
