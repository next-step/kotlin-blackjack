package blackjack.service

import blackjack.domain.Deal
import blackjack.entity.Game
import blackjack.repository.GameRepository

class BlackJackService(private val gameRepository: GameRepository) {
    fun initPlayers(players: List<String>) {
        gameRepository.savePlayers(
            players.map { Game(it) }.toList(),
        )
    }

    fun startGame(): List<Game> {
        val players = gameRepository.findAll()
        players.forEach { initAddCard(it) }
        return players
    }

    private fun initAddCard(game: Game) {
        game.getPlayerBlackJack().addCardCount(Deal.giveCards(INIT_FACE_UP))
    }

    fun gameContinue(player: String): Game {
        val game = gameRepository.findByName(player) ?: throw RuntimeException("Game not found")
        val playerBlackJack = game.getPlayerBlackJack()
        playerBlackJack.addCardCount(Deal.giveCards(DEFAULT_FACE_UP))
        val total = playerBlackJack.getTotalCardValue()
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
        private const val DEFAULT_FACE_UP = 1
    }
}
