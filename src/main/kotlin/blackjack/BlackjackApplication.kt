package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.deck.DefaultDeckGenerator
import blackjack.view.input.CreatePlayerInputView
import blackjack.view.input.PlayerTurnInputView
import blackjack.view.output.PlayerView
import blackjack.view.output.ResultView
import blackjack.view.output.StartPlayersView

fun main() {
    val names = CreatePlayerInputView.parse()
    val blackjackGame = BlackjackGame(names, DefaultDeckGenerator())

    blackjackGame.start()
    val playersDto = blackjackGame.getPlayers()
    StartPlayersView.print(playersDto)

    playersDto.players.forEach { player ->
        while (PlayerTurnInputView.continueDraw(player.name)) {
            if (!blackjackGame.dealCardToPlayer(player.name))
                {
                    break
                }
        }
        val playerDto = blackjackGame.getPlayer(player.name)
        PlayerView.print(playerDto)
    }

    ResultView.print(blackjackGame.getPlayers())
}
