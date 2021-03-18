package blackjack

data class PlayerResult(private val player: Player, var wins: Int = 0, var losses: Int = 0, var draws: Int = 0) {
    val name: String
        get() = player.name

    fun update(playResult: PlayResult) {
        when (playResult) {
            PlayResult.WINS -> wins += 1
            PlayResult.LOSSES -> losses += 1
            PlayResult.DRAWS -> draws += 1
        }
    }
}

infix fun List<Player>.vs(dealer: Player.Dealer): List<PlayerResult> {
    if (dealer.score() > 21) {
        return map { PlayerResult(it, wins = 1) }
    }

    return map { it to (it vs dealer) }
        .map { (player, playResult) ->
            PlayerResult(player)
                .apply {
                    update(playResult)
                }
        } + (dealer vs this)
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
