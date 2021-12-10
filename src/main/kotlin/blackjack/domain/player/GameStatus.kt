package blackjack.domain.player

data class GameStatus(val bet: Bet, val winStatus: WinStatus = WinStatus())
