package blackjack

import blackjack.service.BlackjackService
import blackjack.view.input.CreatePlayerInputView
import blackjack.view.input.PlayerTurnInputView
import blackjack.view.output.PlayerView
import blackjack.view.output.ResultView
import blackjack.view.output.StartPlayersView

fun main() {
    val createPlayersDto = CreatePlayerInputView.print()
    val blackjackGameService = BlackjackService(createPlayersDto)

    blackjackGameService.start()
    val playersDto = blackjackGameService.getPlayers()
    StartPlayersView.print(playersDto)

    playersDto.players.forEach {
        while (PlayerTurnInputView.print(it.name) == "y") {
            blackjackGameService.processTurn(it.name)
        }
        val playerDto = blackjackGameService.getPlayer(it.name)
        PlayerView.print(playerDto)
    }

    ResultView.print(blackjackGameService.getPlayers())
}
