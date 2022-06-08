package blackjack

class Game() {
    fun enter(input: String): List<Player> {
        val players = mutableListOf<Player>()
        for(name in input.split(",")) {
            players.add(Player(name))
        }

        return players
    }
    fun start(names: List<String>) {

    }
}
