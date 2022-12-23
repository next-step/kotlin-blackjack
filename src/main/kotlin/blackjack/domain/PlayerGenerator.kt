package blackjack.domain

class PlayerGenerator {
    fun generate(playerNames: List<String>): List<Player> {
        return playerNames.map { Player(it) }
    }
}
