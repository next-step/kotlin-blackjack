package blackjack.model

@JvmInline
value class Players private constructor(private val players: List<Player>) {

    fun <T> map(transform: (Player) -> T) = players.map(transform)

    fun flatMap(transform: (Player) -> Player) = Players(players.map(transform))

    fun forEach(action: (Player) -> Unit) = players.forEach(action)

    companion object {
        fun of(players: List<Player>) = Players(players)
    }
}
