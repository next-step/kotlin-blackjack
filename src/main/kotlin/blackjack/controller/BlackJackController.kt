package blackjack.controller

import blackjack.dto.GameResult
import blackjack.entity.Game
import blackjack.service.BlackJackService

class BlackJackController(private val blackJackService: BlackJackService) {
    fun initPlayers(players: List<String>) {
        blackJackService.initPlayers(players)
    }

    fun startGame(): List<Game> {
        return blackJackService.startGame()
    }

    fun gameContinue(player: String): Game {
        return blackJackService.gameContinue(player)
    }

    fun getGameResult(): GameResult {
        return blackJackService.getGameResult()
    }
}
