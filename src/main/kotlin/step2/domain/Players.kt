package step2.domain

class Players(
    val players: List<Player>
) {

    companion object {
        fun of(names: List<String>): Players {
            return Players(names.map { Player(it) })
        }
    }
}
