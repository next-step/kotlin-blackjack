package blackjack.domain.player

data class Status private constructor(val name: PlayerName, val gameStatus: GameStatus) {
    fun getWin() = gameStatus.winStatus.win

    fun getLose() = gameStatus.winStatus.lose

    constructor(name: PlayerName, bet: Bet, winStatus: WinStatus = WinStatus()) : this(name, GameStatus(bet, winStatus))
}
