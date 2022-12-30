package blackjack.model

class Players(private val value: List<Player>) : List<Player> by value {
    companion object {
        fun of(vararg player: Player): Players {
            return Players(player.toList())
        }
    }
}
