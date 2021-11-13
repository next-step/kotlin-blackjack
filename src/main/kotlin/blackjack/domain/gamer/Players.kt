package blackjack.domain.gamer

class Players private constructor(
    value: List<Player>,
) {
    val value = value.toList()

    companion object {
        fun from(players: List<Player>): Players {
            return Players(players)
        }
    }
}
