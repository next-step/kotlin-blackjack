package blackJack.dto

import blackJack.domain.player.Dealer
import blackJack.domain.player.GamePlayers
import blackJack.domain.player.Player

class GamePlayersDto(private val players: List<PlayerDto>, val dealer: DealerDto) : List<PlayerDto> by players {
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

class PlayerDto(val name: String, val cards: List<String>, val score: Int) {
    companion object {
        fun of(player: Player): PlayerDto {
            val name = player.name
            val score = player.getScore()
            val cards = player.status.toCards().map {
                "${it.denomination.value}${it.suit.value}"
            }
            return PlayerDto(name, cards, score)
        }
    }
}

class DealerDto(val name: String, val cards: List<String>, val score: Int) {
    companion object {
        fun of(dealer: Dealer): DealerDto {
            val name = dealer.name
            val score = dealer.getScore()
            val cards = dealer.status.toCards().map {
                "${it.denomination.value}${it.suit.value}"
            }

            return DealerDto(name, cards, score)
        }
    }
}
