package blackjack.domain

data class Players(
    val names: List<String>
) {
    var players: List<Player> = names.map{ Player(it) }
}
