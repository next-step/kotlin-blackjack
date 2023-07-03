package blackjack.domain

@JvmInline
value class Players(
    val players: List<Player>,
)

fun Players.forEachPlayer(action: (Player) -> Unit) {
    players.forEach(action)
}
