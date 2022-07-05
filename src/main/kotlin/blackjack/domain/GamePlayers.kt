package blackjack.domain

data class GamePlayers(
    val players: List<Player>,
    val dealer: Dealer
) {
    val allPlayers: List<Player> = playersAndDealer()

    private fun playersAndDealer(): List<Player> {
        val allPlayers = players.toMutableList()
        allPlayers.add(0, dealer)
        return allPlayers.toList()
    }
}
