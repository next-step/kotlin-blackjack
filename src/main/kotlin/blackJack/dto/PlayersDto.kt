package blackJack.dto

import blackJack.domain.Players

data class PlayersDto(val playerDtos: List<PlayerDto>) {
    constructor(players: Players) : this(playerDtos = players.players.map { PlayerDto(it) })
}
