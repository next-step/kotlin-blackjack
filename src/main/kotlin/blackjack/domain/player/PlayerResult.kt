package blackjack.domain.player

data class PlayerResult(val playerStatus: Status) {

    fun getName() = playerStatus.name

    fun getWins() = playerStatus.winStatus.win

    fun getLoses() = playerStatus.winStatus.lose
}
