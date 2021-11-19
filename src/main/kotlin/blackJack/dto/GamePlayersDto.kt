package blackJack.dto

import blackJack.domain.GamePlayer
import blackJack.domain.GamePlayers

class GamePlayersDto(private val players: List<PlayerDto>) : List<PlayerDto> by players {
    fun getPlayerNames(): String = players.joinToString {
        it.name
    }

    companion object {
        fun of(gamePlayers: GamePlayers): GamePlayersDto {
            val value = gamePlayers.map {
                PlayerDto.of(it)
            }
            return GamePlayersDto(value)
        }
    }
}

class PlayerDto(val name: String, val cards: String, val score: Int) {

    companion object {
        fun of(player: GamePlayer): PlayerDto {
            val name = player.name
            val cards = player.status.cards.joinToString {
                "${it.denomination.score(player.getScore())}${it.suit}"
            }
            val score = player.getScore()

            return PlayerDto(name, cards, score)
        }
    }
}
