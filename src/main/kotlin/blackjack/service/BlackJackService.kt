package blackjack.service

import blackjack.domain.Deal
import blackjack.dto.GameResult
import blackjack.dto.PlayerResult
import blackjack.entity.Game
import blackjack.repository.GameRepository

class BlackJackService(private val gameRepository: GameRepository) {
    fun initPlayers(players: List<String>) {
        //딜러 추가

        gameRepository.savePlayers(
            players.map { Game(it) }.toList(),
        )
    }

    fun startGame(): List<Game> {//플레이어 따로 딜러 따로 플레이어만
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

        if (total > BUST_LIMIT_VALUE && DEALER_NAME != player) {
            throw RuntimeException("Player $player has busted with a total of $total")
        }
        return game
    }

    fun getGameResult(): GameResult {
        val games = gameRepository.findAll()
        val dealerScore =
            games.first { DEALER_NAME == it.player }.getPlayerBlackJack().getTotalCardValue()

        return when {
            dealerScore > BUST_LIMIT_VALUE -> dealerLoseResult(games)
            else -> compareGameResults(games, dealerScore)
        }
    }

    private fun dealerLoseResult(games: List<Game>): GameResult {
        val playerResults = games.filter { it.player != DEALER_NAME }.map { player ->
            PlayerResult(
                isPlayer = true,
                playerName = player.player,
                winCount = 1,
                loseCount = 0,
                drawCount = 0
            )
        }

        val dealerResult = PlayerResult(
            isPlayer = false,
            playerName = DEALER_NAME,
            winCount = 0,
            loseCount = playerResults.size,
            drawCount = 0
        )
        return GameResult(listOf(dealerResult) + playerResults)
    }

    private fun compareGameResults(games: List<Game>, dealerScore: Int): GameResult {
        val playerResults = games.filter { it.player != DEALER_NAME }.map { player ->
            playerResult(player, dealerScore)
        }

        val dealerWinCount = playerResults.count { it.loseCount == DEFAULT_COMPARE_RESULT_COUNT }
        val dealerLoseCount = playerResults.count { it.winCount == DEFAULT_COMPARE_RESULT_COUNT }
        val dealerDrawCount = playerResults.count { it.drawCount == DEFAULT_COMPARE_RESULT_COUNT }

        val dealerResult = PlayerResult(
            isPlayer = false,
            playerName = DEALER_NAME,
            winCount = dealerWinCount,
            loseCount = dealerLoseCount,
            drawCount = dealerDrawCount
        )

        return GameResult(listOf(dealerResult) + playerResults)
    }


    private fun playerResult(
        player: Game,
        dealerScore: Int
    ): PlayerResult {
        val playerScore = player.getPlayerBlackJack().getTotalCardValue()
        return when {
            playerScore > dealerScore -> PlayerResult(
                isPlayer = true,
                playerName = player.player,
                winCount = 1,
                loseCount = 0,
                drawCount = 0
            )

            playerScore == dealerScore -> PlayerResult(
                isPlayer = true,
                playerName = player.player,
                winCount = 0,
                loseCount = 0,
                drawCount = 1
            )

            else -> PlayerResult(
                isPlayer = true,
                playerName = player.player,
                winCount = 0,
                loseCount = 1,
                drawCount = 0
            )
        }
    }

    companion object {
        private const val BUST_LIMIT_VALUE = 21
        private const val INIT_FACE_UP = 2
        private const val DEFAULT_FACE_UP = 1
        private const val DEALER_NAME = "딜러"
        private const val DEFAULT_COMPARE_RESULT_COUNT = 1
    }
}
