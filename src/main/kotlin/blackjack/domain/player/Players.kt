package blackjack.domain.player

class Players private constructor(val players: List<Player>) {
    companion object {
        fun of(names: List<String>): Players {
            return Players(names.map { Player(it) }.toList())
        }
    }
}
