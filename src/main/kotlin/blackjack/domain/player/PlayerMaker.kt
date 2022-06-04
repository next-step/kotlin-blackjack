package blackjack.domain.player

class PlayerMaker {

    fun createPlayerByName(playerNames: List<String>): List<Player> {
        return playerNames.map {
            Player(it, mutableSetOf())
        }
    }
}
