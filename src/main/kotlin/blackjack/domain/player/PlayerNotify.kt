package blackjack.domain.player

interface PlayerNotify {
    fun inputPlayer(): MutableList<Player>
    fun inputBettingMoney(players: MutableList<Player>)
}
