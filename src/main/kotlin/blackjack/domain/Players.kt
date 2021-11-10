package blackjack.domain

class Players private constructor(
    value: List<Player>,
) {
    val value = value.toList()

    companion object {
        fun from(playerNames: List<String>): Players {
            val players = playerNames.map { Player(it) }
            return Players(players)
        }
    }
}
