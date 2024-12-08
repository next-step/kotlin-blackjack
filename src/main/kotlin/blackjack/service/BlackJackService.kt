package blackjack.service

import blackjack.domain.Deal
import blackjack.entity.Game
import blackjack.repository.GameRepository

class BlackJackService(private val gameRepository: GameRepository) {
    fun initPlayers(players: List<String>) {
        gameRepository.savePlayers(
            players.map { Game(it) }.toList()
        )
    }

    fun startGame(): List<Game> {
        val players = gameRepository.findAll()
        players.forEach { initAddCard(it) }
        return players
    }

    private fun initAddCard(game: Game) {
        repeat(INIT_FACE_UP) {
            game.getPlayerBlackJack().addCardCount(Deal.giveCard())
        }
    }

    fun gameContinue(player: String): Game {
        val game = gameRepository.findByName(player) ?: throw RuntimeException("Game not found")
        game.getPlayerBlackJack().addCardCount(Deal.giveCard())
        val total = game.getPlayerBlackJack().getTotalCardValue()
        if (total > BUST_LIMIT_VALUE) {
            throw RuntimeException("Player $player has busted with a total of $total")
        }
        return game
    }

    fun getGameResult(): List<Game> {
        return gameRepository.findAll()
    }

    companion object {
        private const val BUST_LIMIT_VALUE = 21
        private const val INIT_FACE_UP = 2
    }
}
