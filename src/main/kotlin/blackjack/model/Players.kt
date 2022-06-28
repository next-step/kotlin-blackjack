package blackjack.model

class Players(val values: List<Player>) {
    fun findNotOver(): List<Player> {
        return values.filter { !it.stay }
    }

    fun stay(player: Player): Players {
        return Players(
            values.map {
                if (player.name == it.name) {
                    player.setStay()
                } else {
                    it
                }
            }
        )
    }

    fun update(player: Player): Players {
        return Players(
            values.map {
                if (player.name == it.name) {
                    player
                } else {
                    it
                }
            }
        )
    }

    fun isAllOver(): Boolean {
        return values.all { it.isGameOver() }
    }

    fun find(name: String): Player? {
        return values.find { it.name == name }
    }

    fun withAllPlayers(f: (Player) -> Player): Players {
        return Players(values.map { f(it) })
    }
}
