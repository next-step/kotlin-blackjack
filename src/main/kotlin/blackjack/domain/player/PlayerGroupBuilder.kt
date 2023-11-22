package blackjack.domain.player

class PlayerGroupBuilder {
    private val players: MutableList<Player> = ArrayList()

    fun players(vararg player: Player) {
        players.addAll(player)
    }

    fun build(): PlayerGroup {
        return PlayerGroup(players)
    }
}

fun playerGroup(block: PlayerGroupBuilder.() -> Unit): PlayerGroup {
    return PlayerGroupBuilder().apply(block).build()
}

