package blackjack

data class PlayerResult(private val player: CardPlayer, var wins: Int = 0, var losses: Int = 0, var draws: Int = 0) {
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

infix fun List<CardPlayer>.vs(dealer: CardPlayer.Dealer): List<PlayerResult> {
    if (dealer.score() > 21) {
        return map { PlayerResult(it, wins = 1) } + PlayerResult(dealer, losses = 1)
    }

    return map { it to (it vs dealer) }
        .map { (player, playResult) ->
            PlayerResult(player)
                .apply {
                    update(playResult)
                }
        } + (dealer vs this)
}

private infix fun CardPlayer.vs(players: List<CardPlayer>): PlayerResult {
    val thisPlayer = this
    return PlayerResult(this)
        .apply {
            for (player in players) {
                update(thisPlayer vs player)
            }
        }
}
