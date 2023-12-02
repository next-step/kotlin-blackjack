package blackjack.domain.batting

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players

data class BetBoard(
    val playerBets: Map<PlayerName, PlayerBet>
) {
    fun playerBet(name: PlayerName): PlayerBet =
        playerBets[name] ?: throw IllegalArgumentException("해당 하는 플레이어 베팅을 찾을 수 없습니다")

    companion object {
        fun of(players: Players, betAmount: (player: Player) -> Amount): BetBoard =
            players.value.associate { it.name to it.playerBet(betAmount) }.let(::BetBoard)

        private fun Player.playerBet(betAmount: (player: Player) -> Amount): PlayerBet =
            PlayerBet.BetPlaced(this.name, betAmount(this))
    }
}
