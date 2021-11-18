package domain.player

data class PlayerInfo(private val playerName: PlayerName, private val betAmount: BetAmount = BetAmount()) {
    fun name() = playerName.name
    fun bet() = betAmount.money
}
