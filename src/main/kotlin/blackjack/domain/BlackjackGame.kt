package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckGenerator
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.dto.PlayerDto
import blackjack.view.dto.PlayersDto

class BlackjackGame(
    names: List<String>,
    deckGenerator: DeckGenerator,
) {
    private val deck: Deck = deckGenerator.generate()
    private val players = Players(names.map { Player(name = it) })

    fun start() {
        players.players.forEach { player ->
            repeat(CARD_DRAW_COUNT_GAME_START) { player.receiveCard(deck.draw()) }
        }
    }

    fun dealCardToPlayer(name: String): Boolean {
        val player = players.findPlayer(name)
        val card = deck.draw()
        return player.receiveCard(card)
    }

    fun getPlayers(): PlayersDto {
        return Players.toDto(players)
    }

    fun getPlayer(name: String): PlayerDto {
        return Player.toDto(players.findPlayer(name))
    }

    companion object {
        private const val CARD_DRAW_COUNT_GAME_START = 2
    }
}
