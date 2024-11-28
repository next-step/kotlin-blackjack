package blackjack.service

import blackjack.domain.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.dto.CardDto
import blackjack.view.dto.CreatePlayersDto
import blackjack.view.dto.PlayerDto
import blackjack.view.dto.PlayersDto

class BlackjackService(createPlayersDto: CreatePlayersDto) {
    private val deck = Deck()
    private val players = Players(createPlayersDto.names.map { Player(name = it) })

    fun start() {
        players.players.forEach { player ->
            repeat(CARD_DRAW_COUNT_GAME_START) { player.receiveCard(deck.draw()) }
        }
    }

    fun processTurn(name: String) {
        val player = findPlayer(name)
        val card = deck.draw()
        player.receiveCard(card)
    }

    fun getPlayers(): PlayersDto {
        return toPlayersDto(players)
    }

    fun getPlayer(name: String): PlayerDto {
        return toPlayerDto(findPlayer(name))
    }

    private fun findPlayer(name: String) =
        players.players.find { it.name == name } ?: throw IllegalStateException(NOT_FOUND_PLAYER_EXCEPTION_MESSAGE)

    companion object {
        private const val CARD_DRAW_COUNT_GAME_START = 2
        private const val NOT_FOUND_PLAYER_EXCEPTION_MESSAGE = "존재하지 않는 플레이어 입니다."

        private fun toPlayerDto(player: Player): PlayerDto =
            PlayerDto(
                name = player.name,
                cards = player.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
            )

        private fun toPlayersDto(players: Players): PlayersDto {
            val playersDto =
                players.players.map {
                    PlayerDto(
                        name = it.name,
                        cards = it.cards.getCards().map { card -> CardDto(card.shape.symbol, card.number.value) },
                    )
                }

            return PlayersDto(playersDto)
        }
    }
}
