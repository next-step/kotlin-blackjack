package blackjack.domain

data class Players(
    val names: List<String>
) {
    val players: List<Player> = names.map { Player(it) }
}
