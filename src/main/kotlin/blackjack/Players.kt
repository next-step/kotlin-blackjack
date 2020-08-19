package blackjack

class Players(private val playerNames: List<String>) {
    private val players: List<Gamer> = addPlayers(playerNames, Cards(mutableListOf(Card.getInstances())))

    private fun addPlayers(playerNames: List<String>, cards: Cards): List<Gamer> {
        val players = mutableListOf<Gamer>(Dealer(cards))

        playerNames.forEach {
            players.add(Player(it, Cards((0..1).map { Card.getInstances() }.toMutableList())))
        }

        return players.toList()
    }

    fun getPlayers() = players
}
