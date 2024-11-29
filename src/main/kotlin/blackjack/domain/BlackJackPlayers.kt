package blackjack.domain

class BlackJackPlayers(val players: List<BlackJackPlayer>) {
    fun getWinPlayer(): List<BlackJackPlayer> {
        val max = players.maxOf { it.getBestSum() }
        return players.filter { it.getBestSum() == max }
    }
}
