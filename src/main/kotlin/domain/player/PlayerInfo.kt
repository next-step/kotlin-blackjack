package domain.player

data class PlayerInfo(private val playerName: PlayerName, private val betAmount: BetAmount = BetAmount()) {
    fun name(): String = playerName.name
    fun bet(): Int = betAmount.money
}
