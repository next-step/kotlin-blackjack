package blackjack.controller

import blackjack.dto.BlackJackResult
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player
import blackjack.service.BlackJackService

class BlackJackController(private val blackJackService: BlackJackService) {
    fun initPlayers(dealerName: String, playersList: List<String>) {
        blackJackService.initPlayers(dealerName, playersList)
    }

    fun startGame(): Game {
        return blackJackService.startGame()
    }
    fun getGameInfo(): Game {
        return blackJackService.getGameInfo()
    }

    fun gameContinue(player: String): Player {
        return blackJackService.gameContinue(player)
    }

    fun getGameResult(): BlackJackResult {
        return blackJackService.getGameResult()
    }

    fun gameContinueDealer(): Dealer {
        return blackJackService.gameContinueDealer()

    }
}
