package blackjack.domain.player

class Players private constructor(val players: List<Player>) {

    companion object {
        fun of(players: List<Player>): Players {
            return Players(players)
        }
    }
}
