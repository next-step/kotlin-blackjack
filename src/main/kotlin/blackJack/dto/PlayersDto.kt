package blackJack.dto

import blackJack.domain.Player
import blackJack.domain.Players

class PlayersDto(private val players: List<PlayerDto>) {

    fun toList(): List<PlayerDto> = players

    fun getPlayerNames(): String = players.joinToString {
        it.name
    }

    companion object {
        fun of(players: Players): PlayersDto {
            val value = players.toList().map {
                PlayerDto.of(it)
            }
            return PlayersDto(value)
        }
    }
}

class PlayerDto(val name: String, val cards: String, val score: Int) {

    companion object {
        fun of(player: Player): PlayerDto {
            val name = player.playerName
            val cards = player.getCards().toList().joinToString {
                "${it.denomination.score}${it.suit}"
            }
            val score = player.status.sumScore()

            return PlayerDto(name, cards, score)
        }
    }
}
