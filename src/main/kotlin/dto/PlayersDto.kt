package dto

import domain.player.Players

class PlayersDto(players: Players) {
    val players: List<PlayerDto> = players.map { PlayerDto(it) }
}
