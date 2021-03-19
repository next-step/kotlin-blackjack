package blackjack

data class PlayerResult(private val player: CardPlayer, val wins: Int = 0, val losses: Int = 0, val draws: Int = 0) {
    val name: String
        get() = player.name

    override fun toString(): String {
        return "PlayerResult(name='$name', wins=$wins, losses=$losses, draws=$draws)"
    }
}

class PlayerResultBuilder(var wins: Int = 0, var losses: Int = 0, var draws: Int = 0) {
    fun update(playResult: PlayResult) {
        when (playResult) {
            PlayResult.WINS -> wins += 1
            PlayResult.LOSSES -> losses += 1
            PlayResult.DRAWS -> draws += 1
        }
    }

    fun build(player: CardPlayer): PlayerResult {
        return PlayerResult(player, wins, losses, draws)
    }
}

fun result(initializer: PlayerResultBuilder.() -> Unit): PlayerResultBuilder {
    return PlayerResultBuilder().apply(initializer)
}
