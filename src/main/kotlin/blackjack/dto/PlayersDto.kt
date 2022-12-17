package blackjack.dto

import blackjack.domain.player.Players

@JvmInline
value class PlayersDto(val players: List<PlayerDto>) {
    fun getNames(): List<String> {
        return players.map { it.name }
    }

    companion object {
        fun from(players: Players): PlayersDto {
            return PlayersDto(players.values.map { PlayerDto.from(it) })
        }
    }
}
