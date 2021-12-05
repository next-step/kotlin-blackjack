package blackjack.domain.player

data class Status(val name: PlayerName, val winStatus: WinStatus = WinStatus())
