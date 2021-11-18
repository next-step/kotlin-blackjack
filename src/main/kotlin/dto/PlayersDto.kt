package dto

import domain.player.Players

data class PlayersDto(val players: List<PlayerDto>) : List<PlayerDto> by players {
    companion object {
        fun from(players: Players) = PlayersDto(players.map { PlayerDto.from(it) })
    }
}
