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
