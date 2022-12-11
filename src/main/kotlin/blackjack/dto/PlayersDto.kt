package blackjack.dto

import blackjack.domain.player.Players

@JvmInline
value class PlayersDto(private val players: Players) {
    fun getPlayers(): List<PlayerDto> {
        return players.values.map { PlayerDto(it) }
    }

    fun getNames(): List<String> {
        return players.values.map { it.name.value }
    }
}
