package blackjack.domain

data class PlayerResult(private val player: CardPlayer, val playResult: PlayResult) {
    val name: String
        get() = player.name

    fun income(bet: Bet): Int {
        if (playResult == PlayResult.DRAWS) {
            return 0
        }
        if (player.blackjack()) {
            return bet.blackjack
        }
        return when (playResult) {
            PlayResult.WINS -> bet.win
            PlayResult.LOSSES -> bet.lost
            else -> 0
        }
    }

    fun income(bettings: List<Bet>): Int {
        return income(bettings.first { it.name == player.name })
    }

    override fun toString(): String {
        return "PlayerResult(name='$name', playResult=$playResult)"
    }
}

infix fun CardPlayer.vs(other: CardPlayer.Dealer): PlayResult {
    if (busts()) {
        return PlayResult.LOSSES
    }
    if (other.busts()) {
        return PlayResult.WINS
    }
    val myScore = score()
    val otherScore = other.score()
    return when {
        myScore > otherScore -> PlayResult.WINS
        myScore < otherScore -> PlayResult.LOSSES
        else -> PlayResult.DRAWS
    }
}
