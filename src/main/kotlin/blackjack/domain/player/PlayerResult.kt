package blackjack.domain.player

data class PlayerResult(val betResult: BetResult, val playerStatus: Status) {

    fun getName() = playerStatus.name

    fun getWins() = playerStatus.getWin()

    fun getLoses() = playerStatus.getLose()
}
