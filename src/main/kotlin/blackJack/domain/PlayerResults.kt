package blackJack.domain

@JvmInline
value class PlayerResults(private val playerResults: List<PlayerResult>) {
    fun toList(): List<PlayerResult> = playerResults.toList()

    companion object {
        fun of(gamePlayers: GamePlayers, dealer: GamePlayer) =
            PlayerResults(
                gamePlayers.toList().map {
                    PlayerResult.winOrLose(it, dealer)
                }
            )
    }
}
