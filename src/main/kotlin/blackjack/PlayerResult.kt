package blackjack

data class PlayerResult(
    private val player: Player,
    var wins: Int = 0,
    var losses: Int = 0,
    var draws: Int = 0
) {
    val name: String
        get() = player.name

    fun update(playResult: Player.Person.PlayResult) {
        when (playResult) {
            Player.Person.PlayResult.WIN -> wins += 1
            Player.Person.PlayResult.LOSSES -> losses += 1
            Player.Person.PlayResult.DRAWS -> draws += 1
        }
    }
}

fun result(builder: PlayResultBuilder.() -> Unit): PlayResultBuilder {
    return PlayResultBuilder().apply(builder)
}

class PlayResultBuilder {
    private val playerResult: MutableList<PlayerResult> = mutableListOf()

    fun update(list: List<PlayerResult>) {
        playerResult.addAll(list)
    }

    fun update(result: PlayerResult) {
        playerResult.add(result)
    }

    fun build(): List<PlayerResult> {
        return playerResult
    }
}

infix fun List<Player>.vs(other: Player): List<PlayerResult> {
    return map { it to (it vs other) }
        .map { (player, playResult) ->
            PlayerResult(player)
                .apply {
                    update(playResult)
                }
        } + (other vs this)
}

private infix fun Player.vs(players: List<Player>): PlayerResult {
    val thisPlayer = this
    return PlayerResult(this)
        .apply {
            for (player in players) {
                update(thisPlayer vs player)
            }
        }
}
