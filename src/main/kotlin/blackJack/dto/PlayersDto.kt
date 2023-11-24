package blackJack.dto

import blackJack.domain.player.Players

data class PlayersDto(val playerDtos: List<PlayerDto>) {
    constructor(players: Players) : this(playerDtos = players.players.map { PlayerDto(it) })
}
