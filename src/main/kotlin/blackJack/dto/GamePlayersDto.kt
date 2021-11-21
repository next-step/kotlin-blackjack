package blackJack.dto

import blackJack.domain.player.Dealer
import blackJack.domain.player.GamePlayers
import blackJack.domain.player.Player

class GamePlayersDto(private val players: List<PlayerDto>, private val dealer: DealerDto) : List<PlayerDto> by players {
    fun getPlayerNames(): String = players.joinToString {
        it.name
    }

    companion object {
        fun of(gamePlayers: GamePlayers): GamePlayersDto {
            val playersDto = gamePlayers.getPlayers().map {
                PlayerDto.of(it)
            }
            val dealerDto = DealerDto.of(gamePlayers.getDealer())
            return GamePlayersDto(playersDto, dealerDto)
        }
    }
}

class PlayerDto(val name: String, val cards: String, val score: Int) {

    companion object {
        fun of(player: Player): PlayerDto {
            val name = player.name
            val cards = player.status.toCards().joinToString {
                "${it.denomination.score(player.getScore())}${it.suit}"
            }
            val score = player.getScore()

            return PlayerDto(name, cards, score)
        }
    }
}

class DealerDto(val name: String, val cards: String, val score: Int) {
    companion object {
        fun of(dealer: Dealer): DealerDto {
            val name = dealer.name
            val cards = dealer.status.toCards().joinToString {
                "${it.denomination.score(dealer.getScore())}${it.suit}"
            }
            val score = dealer.getScore()

            return DealerDto(name, cards, score)
        }
    }
}