package blackjack.service

import blackjack.deal.Deal
import blackjack.entity.Game
import blackjack.repository.GameRepository

class BlackJackService(private val gameRepository: GameRepository) {
    fun initPlayers(players: List<String>) {
        players.forEach {
            gameRepository.savePlayer(Game(it))
        }
    }

    fun startGame(): List<Game> {
        val players = gameRepository.findAll()
        players.forEach { initAddCard(it) }
        return players
    }

    private fun initAddCard(game: Game) {
        repeat(2) {
            game.getPlayerBlackJack().addCardCount(Deal.giveCard())
        }
    }

    fun gameContinue(player: String): Game {
        val game = gameRepository.findByName(player) ?: throw RuntimeException("Game not found")
        game.getPlayerBlackJack().addCardCount(Deal.giveCard())
        return game
    }

    fun getGameResult(): List<Game> {
        return gameRepository.findAll()
    }
}
