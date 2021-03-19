package blackjack

data class PlayerResult(private val player: CardPlayer, val wins: Int = 0, val losses: Int = 0, val draws: Int = 0) {
    val name: String
        get() = player.name

    fun income(bet: Bet): Int {
        if (player.blackjack()) {
            return bet.blackjack
        }
        if (wins > 0) {
            return bet.win
        }
        if (losses > 0) {
            return bet.lost
        }
        return 0
    }

    fun income(bettings: List<Bet>): Int {
        return income(bettings.first { it.name == player.name })
    }

    override fun toString(): String {
        return "PlayerResult(name='$name', wins=$wins, losses=$losses, draws=$draws)"
    }
}

class PlayerResultBuilder(var wins: Int = 0, var losses: Int = 0, var draws: Int = 0) {
    private fun update(playResult: PlayResult) {
        when (playResult) {
            PlayResult.WINS -> wins += 1
            PlayResult.LOSSES -> losses += 1
            PlayResult.DRAWS -> draws += 1
        }
    }

    infix fun CardPlayer.vs(other: CardPlayer.Dealer) {
        if (busts()) {
            update(PlayResult.LOSSES)
            return
        }
        if (other.busts()) {
            update(PlayResult.WINS)
            return
        }
        val myScore = score()
        val otherScore = other.score()
        when {
            myScore > otherScore -> update(PlayResult.WINS)
            myScore < otherScore -> update(PlayResult.LOSSES)
            else -> update(PlayResult.DRAWS)
        }
    }

    fun build(player: CardPlayer): PlayerResult {
        return PlayerResult(player, wins, losses, draws)
    }
}

fun playerResult(initializer: PlayerResultBuilder.() -> Unit): PlayerResultBuilder {
    return PlayerResultBuilder().apply(initializer)
}
