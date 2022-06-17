package blackjack.model.player

class Players(
    private val players: List<Player>
) {

    companion object {
        fun from(names: List<String>) = names.map { Player.from(it) }
    }
}
