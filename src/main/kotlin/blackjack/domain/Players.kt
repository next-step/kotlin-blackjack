package blackjack.domain

class Players private constructor(players: Set<Player>) {
    companion object {
        fun from(names: List<String>): Players =
            Players(
                names.map { Player(Name(it)) }
                    .toSet()
            )
    }
}
