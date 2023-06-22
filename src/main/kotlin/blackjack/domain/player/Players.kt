package blackjack.domain.player

class Players(val value: List<Player>) {
    companion object {
        fun from(vararg player: Player): Players {
            return Players(player.toList())
        }
    }
}
